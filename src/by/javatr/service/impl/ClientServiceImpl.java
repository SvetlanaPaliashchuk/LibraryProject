package by.javatr.service.impl;

import by.javatr.dao.IUserDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.dao.factory.DAOFactory;
import by.javatr.entity.Role;
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
    public void signIn(String login, String password) throws ServiceException {
        try {
            userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Please check login or password");
        }
    }

    @Override
    public void registerUser(User user) throws ServiceException {
        try {
            userDAO.register(user);
        } catch (DAOException e) {
            throw new ServiceException("Registration fail");
        }
    }

    @Override
    public boolean editUser(User user) throws ServiceException {
        return false;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<String> list = null;
        try {
            list = userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException("Could not get the list of users");
        }
        List<User> userList = new ArrayList<>();
        for (String s : list) {
            User user = new User();
            String[] parts = s.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i] != null) {
                    if (parts[i].contains("id=")) {
                        String id = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        user.setId(Integer.parseInt(id));
                    }
                    if (parts[i].contains("name=")) {
                        String name = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        user.setName(name);
                    }
                    if (parts[i].contains("surname=")) {
                        String surname = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        user.setSurname(surname);
                    }
                    if (parts[i].contains("login=")) {
                        String login = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        user.setLogin(login);
                    }
                    if (parts[i].contains("password=")) {
                        String password = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        user.setPassword(password);
                    }
                    if (parts[i].contains("role=")) {
                        String role = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        System.out.println(role);
                        if (role.equals(Role.READER.toString()))
                            user.setRole(Role.READER);
                        else user.setRole(Role.ADMIN);
                    }
                }
            }
            userList.add(user);
        }
        return userList;
    }


    @Override
    public String getUserByLogin(String userLogin) throws ServiceException {
        try {
            return userDAO.getUserByLogin(userLogin);
        } catch (DAOException e) {
            throw new ServiceException("There is no such user");
        }
    }


    @Override
    public boolean deleteUser(String login) throws ServiceException {
        boolean isDeleted = false;
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
