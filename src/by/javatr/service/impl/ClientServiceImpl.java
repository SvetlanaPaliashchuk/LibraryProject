package by.javatr.service.impl;

import by.javatr.dao.IUserDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.dao.factory.DAOFactory;
import by.javatr.entity.User;
import by.javatr.service.ClientService;
import by.javatr.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static final ClientServiceImpl INSTANCE = new ClientServiceImpl();

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private IUserDAO userDAO = daoFactory.getUserDAO();

    private ClientServiceImpl() {
    }

    public static ClientServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean signIn(String login, String password) throws ServiceException {
        if (login == null || password == null) throw new ServiceException("Please check login or password");
        try {
            if (userDAO.signIn(login, password)) return true;
        } catch (DAOException e) {
            throw new ServiceException("Please check login or password");
        }
        return false;
    }

    @Override
    public void registerUser(User user) throws ServiceException {
        if (user == null) throw new ServiceException("Please create the user!");
        try {
            userDAO.register(user);
        } catch (DAOException e) {
            throw new ServiceException("Registration fail");
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> userList = new ArrayList<>();
        try {
            userList = userDAO.getAllUsers();
        } catch (DAOException e) {
            System.out.println("Cannot display users");
        }

        return userList;
    }


    @Override
    public String getUserByLogin(String userLogin) throws ServiceException {
        if (userLogin == null) throw new ServiceException("The user login cannot be null");
        try {
            return userDAO.getUserByLogin(userLogin);
        } catch (DAOException e) {
            throw new ServiceException("There is no such user");
        }
    }


    @Override
    public boolean deleteUser(String login) throws ServiceException {
        if (login == null) throw new ServiceException("The user to delete cannot be null");
        boolean isDeleted;
        try {
            isDeleted = userDAO.deleteUser(login);
        } catch (DAOException e) {
            throw new ServiceException("Could not delete the user");
        }
        return isDeleted;
    }

    @Override
    public boolean deleteAllUsers() throws ServiceException {
        boolean isDeleted = false;
        try {
            isDeleted = userDAO.deleteAllUsers();
        } catch (DAOException e) {
            throw new ServiceException("Could not delete all users");
        }
        return isDeleted;
    }
}
