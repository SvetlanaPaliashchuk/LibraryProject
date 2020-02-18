package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.service.ClientService;
import by.javatr.exception.ServiceException;
import by.javatr.factory.ServiceFactory;

public class DeleteAllUsers implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();
        boolean isDeleted;

        try{
            isDeleted = clientService.deleteAllUsers();
            if (isDeleted) {
                response = "All users have been deleted";
            }
            else   response = "Could not delete all users";
        }

        catch(ServiceException e){
            throw new ServiceException(e);
        }
        return response;
    }
}
