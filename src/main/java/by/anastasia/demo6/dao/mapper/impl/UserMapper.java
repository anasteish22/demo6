package by.anastasia.demo6.dao.mapper.impl;

import by.anastasia.demo6.dao.mapper.Mapper;
import by.anastasia.demo6.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User mapper(ResultSet resultSet) {
        User user = new User();
        try {
            int id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
