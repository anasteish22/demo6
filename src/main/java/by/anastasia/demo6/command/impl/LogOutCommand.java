package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.paths.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return PagePath.INDEX;
    }
}
