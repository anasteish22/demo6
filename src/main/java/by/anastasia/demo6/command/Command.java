package by.anastasia.demo6.command;

import by.anastasia.demo6.exception.CommandException;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws DaoException, CommandException, ServiceException;
    default void refresh(){}
}
