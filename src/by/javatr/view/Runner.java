package by.javatr.view;


import by.javatr.controller.Controller;
import by.javatr.exception.ServiceException;

public class Runner {
    public static void main(String[] args) {
        //       User user = new User("Svetlana", "Yuntsevich", "Lana", "password", Role.READER);
        Menu menu = new Menu();
        menu.start();

         //  Controller c = new Controller();

//        try {
//            System.out.println(c.executeTask("sign_in Lana 123"));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

        //Book book = new Book("aaa", "bbb", 258);

    }
}
