package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.Command;
import by.javatr.service.BookService;
import by.javatr.exception.ServiceException;
import by.javatr.factory.ServiceFactory;

public class GetBookByTitle implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        String text = request.trim();
        String[] words = text.split(" ");
        String title = words[1];
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();

        try {
            response = "Book info:\n" + bookService.getBookByName(title);
        } catch (ServiceException e) {
            throw new ServiceException();
        }
        return response;
    }
}
