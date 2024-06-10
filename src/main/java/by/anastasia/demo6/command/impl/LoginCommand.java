package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.service.UserService;
import by.anastasia.demo6.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String page;
        if (userService.authenticate(login, password)) {
            request.setAttribute("user", login);
            page = "pages/main.jsp";
        } else {
            request.setAttribute("login_msg", "incorrect login or path");
            page = "index.jsp";
        }
        return page;
    }
}
