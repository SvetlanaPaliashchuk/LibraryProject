package by.javatr.service;

import by.javatr.entity.Book;
import by.javatr.entity.User;
import by.javatr.service.exception.ServiceException;

import java.util.List;

public class ResultChecker {

	public static void checkBooksList(List<Book> books)
			throws ServiceException {
		if(books.isEmpty()) {
			throw new ServiceException("Oops! Looks like there are "
					+ "no books available.");
		}
	}
	
	public static void checkBook(Book book) throws ServiceException {
		if(book == null) {
			throw new ServiceException("Oops! Looks like the "
					+ "data for this book is not available!");
		}
	}
	
	public static void checkUsers(List<User> users) throws ServiceException {
		if(users.isEmpty()) {
			throw new ServiceException("No Users registered!");
		}
	}

	}

