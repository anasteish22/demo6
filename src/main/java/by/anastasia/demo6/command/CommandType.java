package by.anastasia.demo6.command;

import by.anastasia.demo6.command.impl.AddUserCommand;
import by.anastasia.demo6.command.impl.DefaultCommand;
import by.anastasia.demo6.command.impl.LoginCommand;
import by.anastasia.demo6.command.impl.LogoutCommand;

public enum CommandType {
    ADDUSER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
