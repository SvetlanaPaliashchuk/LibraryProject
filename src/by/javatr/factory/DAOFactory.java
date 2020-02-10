package by.javatr.factory;

import by.javatr.dao.BookDAO;
import by.javatr.dao.UserDAO;
import by.javatr.dao.impl.BookDAOImpl;
import by.javatr.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private static final UserDAO USER_DAO_INSTANCE = UserDAOImpl.getInstance();
	private static final BookDAO BOOK_DAO_INSTANCE = BookDAOImpl.getInstance();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return INSTANCE;
	}
	
	public UserDAO getUserDAO() {
		return USER_DAO_INSTANCE;
	}
	
	public BookDAO getBookDao() {
		return BOOK_DAO_INSTANCE;
	}

}