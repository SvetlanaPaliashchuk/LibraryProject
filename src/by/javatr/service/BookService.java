package by.javatr.service;

import by.javatr.entity.Book;
import by.javatr.service.exception.ServiceException;
import java.util.List;

public interface BookService {
	List<Book> getBooksList() throws ServiceException;

	String getBookByName(String bookName) throws ServiceException;

	List<Book> getBooksListByAuthor(String author) throws ServiceException;

	boolean addBook(Book book) throws ServiceException;

	boolean deleteBook(String id)throws ServiceException;


}