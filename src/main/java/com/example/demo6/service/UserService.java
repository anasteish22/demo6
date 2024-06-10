package com.example.demo6.service;

import com.example.demo6.model.User;

public interface UserService {
    boolean authenticate(String login, String password);
    boolean saveUser(User user);
}
