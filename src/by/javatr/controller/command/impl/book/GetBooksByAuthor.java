package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.Command;
import by.javatr.entity.Book;
import by.javatr.service.BookService;
import by.javatr.exception.ServiceException;
import by.javatr.factory.ServiceFactory;

import java.util.List;

public class GetBooksByAuthor implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        List<Book> list;
        String text = request.trim();
        String[] words = text.split(" ");
        String author = words[1];
        StringBuilder response  = new StringBuilder();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();

        try {
            list = bookService.getBooksListByAuthor(author);
            response.append("Author: ");
            response.append(author);
            response.append("\nList of books:\n");
            for (Book book : list) {
                response.append(book);
                response.append("\n");
            }
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return response.toString();
    }
}
