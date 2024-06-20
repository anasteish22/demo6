package by.anastasia.demo6.pool;

import by.anastasia.demo6.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {
    private BlockingQueue<Connection> connections = new LinkedBlockingQueue<>(4);
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;

    private ConnectionPool() throws ConnectionException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ConnectionException(e);
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

        for (int i = 0; i < 4; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                connections.put(connection);
            } catch (SQLException | InterruptedException e) {
                throw new ConnectionException(e);
            }
        }
    }

    public static ConnectionPool getInstance() throws ConnectionException {
        if (isCreated.compareAndSet(false, true)) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws ConnectionException {
        Connection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionException(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionException {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionException(e);
        }
    }

    public void closePool() throws ConnectionException {
        for (int i = 0; i < connections.size(); i++) {
            try {
                connections.take().close();
            } catch (SQLException | InterruptedException e) {
                throw new ConnectionException(e);
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
