package by.anastasia.demo6.service;

import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.model.User;

import java.util.List;

public interface UserService {
    boolean authenticate(String login, String password);
    boolean saveUser(User user);
    List<User> viewAll();
}
