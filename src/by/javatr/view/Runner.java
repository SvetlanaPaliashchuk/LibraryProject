package by.javatr.view;

import by.javatr.controller.Controller;
import by.javatr.dao.IUserDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.dao.factory.DAOFactory;
import by.javatr.entity.Book;
import by.javatr.entity.Role;
import by.javatr.entity.User;
import by.javatr.scanner.DataScanner;

import javax.xml.crypto.Data;

public class Runner {
    public static void main(String[] args) {
        User user = new User("Svetlana", "Yuntsevich", "Lana", "password", Role.READER);
//        Menu menu = new Menu();
//        menu.start();

        Controller c = new Controller();

        System.out.println(c.executeTask("get_user_by_login Lana"));
    }
}
