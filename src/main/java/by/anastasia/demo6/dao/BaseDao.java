package by.anastasia.demo6.dao;

import by.anastasia.demo6.exception.ConnectionException;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.model.Entity;
import by.anastasia.demo6.pool.ConnectionPool;
import by.anastasia.demo6.pool.ConnectionPoolArrayDeque;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <T extends Entity> {
    List<T> findAll() throws DaoException, ConnectionException;
    T findEntityById(int id);
    boolean delete(T t);
    boolean delete(int id);
    boolean save(T t) throws DaoException, ConnectionException;
    T update(T t);
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
        }
    }

    default void close(Connection connection) throws ConnectionException {
        if (connection != null) {
            ConnectionPoolArrayDeque.getInstance().release(connection);
        }
    }
    default void close(ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
