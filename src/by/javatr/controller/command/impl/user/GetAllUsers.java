package by.javatr.controller.command.impl.user;

import by.javatr.controller.command.Command;
import by.javatr.entity.User;
import by.javatr.service.ClientService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

import java.util.List;

public class GetAllUsers implements Command {

    @Override
    public String execute(String request) {
        StringBuilder response  = new StringBuilder();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();

        try{
            List<User> list = clientService.getAllUsers();
            response.append("List of users:\n");
            for (User user : list) {
                String s = user.toString();
                String s2 = "";
                if (s.contains("password=")){
                    s2 = s.replaceFirst(" password=.+?,", "");
                }

                response.append(s2);
                response.append("\n");
            }
        }catch(ServiceException e){
            response.append("Could not show all users");
        }
        return response.toString();
    }
}
