package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.service.ClientService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

public class DeleteAllUsers implements Command {
    @Override
    public String execute(String request) {
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();
        boolean isDeleted = false;

        try{
            isDeleted = clientService.deleteAllUsers();
            if (isDeleted) response = "All users have been deleted";
            else   response = "Could not delete all users";
        }

        catch(ServiceException e){
            response = "Could not delete all users";
        }
        return response;
    }
}
