package by.anastasia.demo6.dao.impl;

import by.anastasia.demo6.command.ParameterRequest;
import by.anastasia.demo6.dao.ColumnName;
import by.anastasia.demo6.dao.UserDao;
import by.anastasia.demo6.dao.mapper.impl.UserMapper;
import by.anastasia.demo6.exception.ConnectionException;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.pool.ConnectionPool;
import by.anastasia.demo6.pool.ConnectionPoolArrayDeque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    private static final String ADD_USER = "INSERT INTO users (first_name, last_name, login, password, phone_number) values (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_USERS = "SELECT id, first_name, last_name, login, password, phone_number FROM users";
    private static final String SELECT_LOGINS = "SELECT login FROM users";
    private static final String SELECT_PHONES = "SELECT phone_number FROM users";
    @Override
    public boolean login(String login, String password) throws DaoException, ConnectionException {
        boolean match = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolArrayDeque.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String pass = resultSet.getString(ColumnName.USERS_PASSWORD);
                match = password.equals(pass);
            }
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(resultSet);
            close(connection);
        }
        return match;
    }


    @Override
    public boolean save(User user) throws DaoException, ConnectionException {
        boolean done = false;
        Connection connection = null;
        PreparedStatement statementAdd = null;
        try {
            connection = ConnectionPoolArrayDeque.getInstance().getConnection();
            statementAdd = connection.prepareStatement(ADD_USER);

            if (!hasLogin(connection, user.getLogin()) && !hasPhone(connection, user.getPhoneNumber())) {
                statementAdd.setString(1, user.getFirstName());
                statementAdd.setString(2, user.getLastName());
                statementAdd.setString(3, user.getLogin());
                statementAdd.setString(4, user.getPassword());
                statementAdd.setString(5, user.getPhoneNumber());
                statementAdd.execute();
                done = true;
            }
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            close(statementAdd);
            close(connection);
        }
        return done;
    }

    private boolean hasLogin(Connection connection, String login) throws DaoException {
        boolean hasLogin = false;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_LOGINS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String log = resultSet.getString(ColumnName.USERS_LOGIN);
                hasLogin = login.equals(log);
                if (hasLogin) {
                    break;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
            return hasLogin;
    }

    private boolean hasPhone(Connection connection, String phone) throws DaoException {
        boolean hasPhone = false;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_PHONES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ph = resultSet.getString(ColumnName.USERS_PHONE);
                hasPhone = phone.equals(ph);
                if (hasPhone) {
                    break;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return hasPhone;
    }

    @Override
    public List<User> findAll() throws DaoException, ConnectionException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolArrayDeque.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                User user = new UserMapper().mapper(resultSet);
                users.add(user);
            }
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(resultSet);
            close(connection);
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
