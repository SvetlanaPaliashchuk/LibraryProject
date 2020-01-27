package by.javatr.controller;

import by.javatr.controller.command.Command;

public final class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimiter = ' ';

    public String executeTask(String request){
        String commandName;
        Command executionCommand;

        if (request.contains(" ")) {
            commandName = request.substring(0, request.indexOf(paramDelimiter));
        }
        else commandName = request;
        executionCommand = provider.getCommand(commandName);

        String response = executionCommand.execute(request);

        return response;
    }
}
