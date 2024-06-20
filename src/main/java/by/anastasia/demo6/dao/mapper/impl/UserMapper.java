package by.anastasia.demo6.dao.mapper.impl;

import by.anastasia.demo6.dao.ColumnName;
import by.anastasia.demo6.dao.mapper.Mapper;
import by.anastasia.demo6.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User mapper(ResultSet resultSet) {
        User user = new User();
        try {
            int id = resultSet.getInt(ColumnName.USERS_ID);
            String login = resultSet.getString(ColumnName.USERS_LOGIN);
            String firstName = resultSet.getString(ColumnName.USERS_FIRST_NAME);
            String lastName = resultSet.getString(ColumnName.USERS_LAST_NAME);
            String password = resultSet.getString(ColumnName.USERS_PASSWORD);
            String phone = resultSet.getString(ColumnName.USERS_PHONE);

            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setPassword(password);
            user.setPhoneNumber(phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
