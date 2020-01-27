package by.javatr.dao;

import by.javatr.dao.exception.DAOException;
import by.javatr.entity.User;

import java.util.List;

public interface IUserDAO {

    boolean signIn(String login, String password)throws DAOException;

    void register(User user)throws DAOException;

    boolean deleteAllUsers()throws DAOException;

    boolean deleteUser(String login)throws DAOException;

    List<User> getAllUsers() throws DAOException;

    String getUserByLogin(String userLogin)throws DAOException;

}
