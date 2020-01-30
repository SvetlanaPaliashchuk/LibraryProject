package by.javatr.controller;

import by.javatr.controller.command.Command;
import by.javatr.service.exception.ServiceException;

public final class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimiter = ' ';

    public String executeTask(String request) throws ServiceException {
        String commandName;
        Command executionCommand;

        if (request.contains(" ")) {
            commandName = request.substring(0, request.indexOf(paramDelimiter));
        }
        else commandName = request;
        executionCommand = provider.getCommand(commandName);

        return executionCommand.execute(request);
    }
}
