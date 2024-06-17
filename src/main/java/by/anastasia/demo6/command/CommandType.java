package by.anastasia.demo6.command;

import by.anastasia.demo6.command.impl.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    VIEW_ALL_USERS(new ViewAllUsersCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        return Arrays.stream(CommandType.values())
                .filter(c -> c.name().equals(commandStr.toUpperCase()))
                .findFirst()
                .orElse(CommandType.DEFAULT)
                .command;
    }

    public static String requestUri (HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] uriParts = uri.split("/");
        uri = uriParts[uriParts.length - 1];
        String commandStr = uri.split("\\.")[0];
        return commandStr;
    }
}
