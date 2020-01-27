package by.javatr.dao.factory;

import by.javatr.dao.IBookDAO;
import by.javatr.dao.IUserDAO;
import by.javatr.dao.impl.BookDAOImpl;
import by.javatr.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private static final IUserDAO USER_DAO_INSTANCE = UserDAOImpl.getInstance();
	private static final IBookDAO BOOK_DAO_INSTANCE = BookDAOImpl.getInstance();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return INSTANCE;
	}
	
	public IUserDAO getUserDAO() {
		return USER_DAO_INSTANCE;
	}
	
	public IBookDAO getBookDao() {
		return BOOK_DAO_INSTANCE;
	}

}