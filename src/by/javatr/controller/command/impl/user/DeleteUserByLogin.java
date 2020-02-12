package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.service.ClientService;
import by.javatr.exception.ServiceException;
import by.javatr.factory.ServiceFactory;

public class DeleteUserByLogin implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        String text = request.trim();
        String[] words = text.split(" ");
        String response;
        String login = words[1];

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();

        try{
            if (clientService.deleteUserByLogin(login))
            {
                response= "The user "+ login + " has been deleted";
            }
            else response = "The user "+ login + " has not been deleted";
        }
        catch(ServiceException e){
            throw new ServiceException();
        }
        return response;
    }
}
