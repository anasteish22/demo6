package by.anastasia.demo6.pool;

import by.anastasia.demo6.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolArrayDeque {
    private static ConnectionPoolArrayDeque instance;
    private static final int POOL_SIZE = 4;
    private ArrayDeque<ProxyConnection> freeConnections = new ArrayDeque<>(POOL_SIZE);
    private static Lock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();

    private ConnectionPoolArrayDeque() throws ConnectionException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }

        String url = "jdbc:mysql://localhost:3306/usersdb";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");
        properties.put("useSSl", "true");
        properties.put("useJBCCompliantTimezoneShift", "true");
        properties.put("useLegacyDatetimeCode", "false");
        properties.put("serverTimezone", "UTC");
        properties.put("serverSslCert", "classpath:server.crt");

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                throw new ConnectionException(e);
            }
        }
    }

    public static ConnectionPoolArrayDeque getInstance() throws ConnectionException {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPoolArrayDeque();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            lock.lock();
            connection = freeConnections.poll();
        } finally {
            lock.unlock();
        }
        return connection;
    }

    public void release(Connection connection) {
        if (connection instanceof ProxyConnection proxyConnection) {
            freeConnections.push(proxyConnection);
        }
    }

    public void closePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.peek().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        deregisterDrivers();
    }
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
            }
        });
    }
}
