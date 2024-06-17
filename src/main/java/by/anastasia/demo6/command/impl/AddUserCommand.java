package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.service.UserService;
import by.anastasia.demo6.service.impl.UserServiceImpl;
import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.model.User;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    private UserService service = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws DaoException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        boolean done = service.saveUser(user);
        return "pages/result.jsp";
    }
}
