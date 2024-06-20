package by.anastasia.demo6.service.impl;

import by.anastasia.demo6.dao.UserDao;
import by.anastasia.demo6.dao.impl.UserDaoImpl;
import by.anastasia.demo6.exception.ConnectionException;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.exception.ServiceException;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        try {
            return userDao.login(login, password);
        } catch (DaoException | ConnectionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean saveUser(User user) throws ServiceException {
        try {
            return userDao.save(user);
        } catch (DaoException | ConnectionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> viewAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException | ConnectionException e) {
            throw new ServiceException(e);
        }
    }
}
