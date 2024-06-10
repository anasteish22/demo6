package by.anastasia.demo6.service.impl;

import by.anastasia.demo6.dao.UserDao;
import by.anastasia.demo6.dao.impl.UserDaoImpl;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean authenticate(String login, String password) {
        return userDao.login(login, password);
    }

    @Override
    public boolean saveUser(User user) {
        return userDao.save(user);
    }
}
