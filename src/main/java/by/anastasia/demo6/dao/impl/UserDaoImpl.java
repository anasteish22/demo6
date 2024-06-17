package by.anastasia.demo6.dao.impl;

import by.anastasia.demo6.dao.UserDao;
import by.anastasia.demo6.dao.mapper.impl.UserMapper;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    private static final String ADD_USER = "INSERT INTO users (login, password) values (?, ?)";
    private static final String FIND_ALL_USERS = "SELECT id, login, password FROM phones";

    @Override
    public boolean login(String login, String password) {
        boolean match = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String pass = resultSet.getString("password");
                match = password.equals(pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(resultSet);
            close(connection);
        }
        return match;
    }


    @Override
    public boolean save(User user) {
        boolean done = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            done = statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e); //todo
        } finally {
            close(statement);
            close(connection);
        }
        return done;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS)) {
            while (resultSet.next()) {
                User user = new UserMapper().mapper(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findEntityById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User update(User user) {
        throw new UnsupportedOperationException();
    }
}
