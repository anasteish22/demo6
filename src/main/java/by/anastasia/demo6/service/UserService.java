package by.anastasia.demo6.service;

import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.exception.ServiceException;
import by.anastasia.demo6.model.User;

import java.util.List;

public interface UserService {
    boolean authenticate(String login, String password) throws DaoException, ServiceException;
    boolean saveUser(User user) throws ServiceException;
    List<User> viewAll() throws ServiceException;
}
