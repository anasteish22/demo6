package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.command.AttributeRequest;
import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.command.MessageRequest;
import by.anastasia.demo6.command.ParameterRequest;
import by.anastasia.demo6.exception.CommandException;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.exception.ServiceException;
import by.anastasia.demo6.paths.PagePath;
import by.anastasia.demo6.service.UserService;
import by.anastasia.demo6.service.impl.UserServiceImpl;
import by.anastasia.demo6.util.PasswordEncoded;
import jakarta.servlet.http.HttpServletRequest;

public class LogInCommand implements Command {
    private UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(ParameterRequest.LOGIN);
        String password = request.getParameter(ParameterRequest.PASSWORD);
        password = new PasswordEncoded().encoded(password);
        String page;
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute(AttributeRequest.USER, login);
                page = PagePath.MAIN;
            } else {
                request.setAttribute(AttributeRequest.LOGIN_MSG, MessageRequest.WRONG_LOGIN);
                page = PagePath.INDEX;
            }
        } catch (DaoException | ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
