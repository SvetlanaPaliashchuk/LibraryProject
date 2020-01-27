package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.Command;
import by.javatr.service.BookService;
import by.javatr.service.ClientService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

public class GetBookByTitle implements Command {
    @Override
    public String execute(String request) {
        String text = request.trim();
        String[] words = text.split(" ");
        String title = words[1];
        String response = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();

        try {
            response = "Book info:\n" + bookService.getBookByName(title);
        } catch (ServiceException e) {
            response = "No book info";
        }
        return response;
    }
}
