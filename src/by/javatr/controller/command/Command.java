package by.javatr.controller.command;

import by.javatr.exception.ServiceException;

public interface Command {
    String execute(String request) throws ServiceException;
}
