package by.anastasia.demo6.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private BlockingQueue<Connection> connections = new LinkedBlockingQueue<>(4);
    private static ConnectionPool instance;

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e); //fixme
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
                throw new RuntimeException(e); //fixme
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //fixme
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //fixme
        }
    }
}
