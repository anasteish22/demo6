package by.anastasia.demo6.command;

import by.anastasia.demo6.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws DaoException;
    default void refresh(){}
}
