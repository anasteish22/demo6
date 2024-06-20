package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.paths.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.INDEX;
    }
}
