package com.example.demo6.command.impl;

import com.example.demo6.command.Command;
import com.example.demo6.model.User;
import com.example.demo6.service.UserService;
import com.example.demo6.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    private UserService service = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        boolean done = service.saveUser(user);
        return "pages/result.jsp";
    }
}
