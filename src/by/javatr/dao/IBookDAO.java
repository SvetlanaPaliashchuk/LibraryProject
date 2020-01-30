package by.javatr.dao;

import by.javatr.dao.exception.DAOException;
import by.javatr.entity.Book;

import java.util.List;

public interface IBookDAO {
    boolean addBook(Book book) throws DAOException;

    boolean deleteBook(String id) throws DAOException;

    List<Book> getAllBooks() throws DAOException;

    String getBookByTitle(String title) throws DAOException;

    List<Book> getBooksByAuthor(String author) throws DAOException;
}
