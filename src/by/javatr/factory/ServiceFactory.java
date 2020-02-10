package by.javatr.factory;

import by.javatr.service.BookService;
import by.javatr.service.ClientService;
import by.javatr.service.impl.BookServiceImpl;
import by.javatr.service.impl.ClientServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private static final ClientService CLIENT_SERVICE_INSTANCE = ClientServiceImpl.getInstance();
    private static final BookService BOOK_SERVICE_INSTANCE = BookServiceImpl.getInstance();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public ClientService getUserService() {
        return CLIENT_SERVICE_INSTANCE;
    }

    public BookService getBookService() {
        return BOOK_SERVICE_INSTANCE;
    }

}
