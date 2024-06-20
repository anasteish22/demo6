package by.anastasia.demo6.dao;

import by.anastasia.demo6.exception.ConnectionException;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.model.User;

public interface UserDao extends BaseDao<User> {
    boolean login(String login, String password) throws DaoException, ConnectionException;
}
