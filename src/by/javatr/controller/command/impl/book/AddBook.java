package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.Command;
import by.javatr.entity.Book;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

public class AddBook implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        String text = request.trim();
        String[] params = text.split(" ");
        String title = params[1];
        String author = params[2];
        int pages = Integer.parseInt(params[3]);

        Book book = new Book(title, author, pages);

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();

        try {
            boolean isAdded = bookService.addBook(book);
            if (isAdded) response = "Book has been added to the library";
            else response = "Error during adding book procedure";
        } catch (ServiceException e) {
            throw new ServiceException();
        }
        return response;
    }
    }

