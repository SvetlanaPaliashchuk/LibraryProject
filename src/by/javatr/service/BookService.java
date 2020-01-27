package by.javatr.service;

import by.javatr.entity.Book;
import by.javatr.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface BookService {
	List<String> getBooksList() throws ServiceException;

	String getBookByName(String bookName) throws ServiceException;

	List<Book> getBooksListByAuthor(String author) throws ServiceException;

	boolean addBook(Book book) throws ServiceException;
	
	boolean editBook(Map<String, Object> requestParameters, String language) throws ServiceException;

	boolean deleteBook(String id)throws ServiceException;


}