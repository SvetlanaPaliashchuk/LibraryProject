package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.service.ClientService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

public class DeleteUser implements Command {
    @Override
    public String execute(String request) {
        String text = request.trim();
        String[] words = text.split(" ");
        String response = null;
        String login = words[1];

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();

        try{
            clientService.deleteUser(login);
            response= "The user "+ login + " has been deleted";
        }
        catch(ServiceException e){
            response = "Error during delete procedure";
        }
        return response;
    }
}
