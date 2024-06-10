package by.anastasia.demo6.dao;

import by.anastasia.demo6.model.User;

public interface UserDao {
    boolean login(String login, String password);
    boolean save(User user);
}
