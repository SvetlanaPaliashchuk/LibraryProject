package by.javatr.dao;

import by.javatr.dao.exception.DAOException;
import by.javatr.entity.Book;
import by.javatr.entity.User;

import java.util.List;

public interface IBookDAO {
    boolean addBook(Book book) throws DAOException;

    boolean deleteBook(String id) throws DAOException;

    List<String> getAllBooks() throws DAOException;

    String getBookByTitle(String title) throws DAOException;
}
