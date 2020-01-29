package by.javatr.view;

import by.javatr.controller.Controller;

import by.javatr.entity.Book;
import by.javatr.entity.Role;
import by.javatr.entity.User;


public class Runner {
    public static void main(String[] args) {
 //       User user = new User("Svetlana", "Yuntsevich", "Lana", "password", Role.READER);
//        Menu menu = new Menu();
//        menu.start();

        Controller c = new Controller();

        System.out.println(c.executeTask("get_books_by_author njio"));

        Book book = new Book("aaa", "bbb", 258);

    }
}
