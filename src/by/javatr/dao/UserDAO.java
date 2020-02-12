package by.javatr.dao;

import by.javatr.exception.DAOException;
import by.javatr.entity.User;

import java.util.List;

public interface UserDAO {

    boolean signIn(String login, String password)throws DAOException;

    void register(User user)throws DAOException;

    boolean deleteAllUsers()throws DAOException;

    boolean deleteUserByLogin(String login)throws DAOException;

    List<User> getAllUsers() throws DAOException;

    String getUserByLogin(String userLogin)throws DAOException;

}
