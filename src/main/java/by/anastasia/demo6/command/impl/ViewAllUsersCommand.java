package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.service.UserService;
import by.anastasia.demo6.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ViewAllUsersCommand implements Command {
    UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws DaoException {
        List<User> users = userService.viewAll();
        return "pages/view_all_users.jsp";
    }
}
