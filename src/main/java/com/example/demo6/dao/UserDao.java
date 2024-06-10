package com.example.demo6.dao;

import com.example.demo6.model.User;

public interface UserDao {
    boolean login(String login, String password);
    boolean save(User user);
}
