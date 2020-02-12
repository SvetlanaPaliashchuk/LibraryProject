package by.javatr.dao;

import by.javatr.exception.DAOException;
import by.javatr.entity.Book;

import java.util.List;

public interface BookDAO {
    boolean addBook(Book book) throws DAOException;

    boolean deleteBookByID(String id) throws DAOException;

    List<Book> getAllBooks() throws DAOException;

    String getBookByTitle(String title) throws DAOException;

    List<Book> getBooksByAuthor(String author) throws DAOException;
}
