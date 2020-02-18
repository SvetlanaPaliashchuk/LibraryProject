package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.service.ClientService;
import by.javatr.exception.ServiceException;
import by.javatr.factory.ServiceFactory;

public class SignIn implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        String text = request.trim();
        String[] words = text.split(" ");
        // if (words.length<3) throw new Exception(" ");
        String login = words[1];
        String password = words[2];
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();

        try {
            if (clientService.signIn(login, password))
            {
                response = "Welcome, " + login;
            }
            else throw new ServiceException();
            //else response = "Please check login or password";
        } catch (ServiceException e) {
           // response = "Error during login procedure";
            throw new ServiceException(e);
        }
        return response;
    }
}
