package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.Command;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

public class DeleteBook implements Command {
    @Override
    public String execute(String request) {
        String text = request.trim();
        String[] params = text.split(" ");
        String response;
        String id = params[1];

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        try{

            boolean isDeleted = bookService.deleteBook(id);
            if (isDeleted) response= "The book has been deleted";
            else response = "There is no such book";
        }
        catch(ServiceException e){
            response = "Error during delete procedure";
        }
        return response;
    }
}
