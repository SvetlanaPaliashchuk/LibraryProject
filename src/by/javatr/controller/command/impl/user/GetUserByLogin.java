package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.service.ClientService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

public class GetUserByLogin implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        String text = request.trim();
        String[] words = text.split(" ");
        //if (words.length<3) throw new Exception(" ");
        String login = words[1];
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();

        try {
            String s = clientService.getUserByLogin(login);
            if (s.contains("password=")){
                s = s.replaceFirst(" password=.+?,", "");
            }
            response = "User info:\n" + s;
        } catch (ServiceException e) {
            throw new ServiceException();

        }
        return response;
    }
}
