package by.javatr.service.impl;

import by.javatr.dao.IBookDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.dao.factory.DAOFactory;
import by.javatr.entity.Book;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private static final BookServiceImpl INSTANCE = new BookServiceImpl();

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private IBookDAO bookDAO = daoFactory.getBookDao();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Book> getBooksList() throws ServiceException {
        List<Book> list;
        try {
            list = bookDAO.getAllBooks();
            if (list == null) throw new ServiceException("No books in the library");
        } catch (DAOException e) {
            throw new ServiceException("Could not get the list of books");
        }
        return list;
    }

    @Override
    public String getBookByName(String bookName) throws ServiceException {
        if (bookName == null) throw new ServiceException("The book title cannot be null");
        try {
            return bookDAO.getBookByTitle(bookName);
        } catch (DAOException e) {
            throw new ServiceException("There is no such book");
        }
    }
    @Override
    public List<Book> getBooksListByAuthor(String author) throws ServiceException {
        if (author == null) throw new ServiceException("The book author cannot be null");
        try{
            return bookDAO.getBooksByAuthor(author);

        }
        catch (DAOException e) {
            throw new ServiceException("Could not add the book");
        }
    }

    @Override
    public boolean addBook(Book book) throws ServiceException {
        if (book == null) throw new ServiceException("Please create the book!");
        try{
            return bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new ServiceException("Could not add the book");
        }
    }

    @Override
    public boolean deleteBook(String id) throws ServiceException {
        if (id == null) throw new ServiceException("The book id cannot be null");
        try {
            return bookDAO.deleteBook(id);
        } catch (DAOException e) {
            throw new ServiceException("Could not delete the book");
        }
    }

}
