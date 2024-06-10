package by.anastasia.demo6.dao.impl;

import by.anastasia.demo6.dao.UserDao;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.pool.ConnectionPool;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    private static final String ADDUSER = "INSERT INTO users (login, password) values (?, ?)";

    @Override
    public boolean login(String login, String password) {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    String pass = resultSet.getString("password");
                    match = password.equals(pass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    @Override
    public boolean save(User user) {
        boolean done = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADDUSER)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            done = statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e); //todo
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return done;
    }
}
