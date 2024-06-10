package com.example.demo6.command;

import com.example.demo6.command.impl.AddUserCommand;
import com.example.demo6.command.impl.DefaultCommand;
import com.example.demo6.command.impl.LoginCommand;
import com.example.demo6.command.impl.LogoutCommand;

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
