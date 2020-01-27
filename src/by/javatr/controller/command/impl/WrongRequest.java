package by.javatr.controller.command.impl;

import by.javatr.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "Wrong request";
    }
}
