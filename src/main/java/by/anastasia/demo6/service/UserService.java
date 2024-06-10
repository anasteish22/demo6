package by.anastasia.demo6.service;

import by.anastasia.demo6.model.User;

public interface UserService {
    boolean authenticate(String login, String password);
    boolean saveUser(User user);
}
