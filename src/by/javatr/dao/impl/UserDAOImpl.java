package by.javatr.dao.impl;

import by.javatr.dao.IUserDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private static final String fileName = "E:\\LibraryYuntsevich\\src\\by\\javatr\\IOData\\users.txt";
    private static final UserDAOImpl INSTANCE = new UserDAOImpl();
    private List<User> userList;

    private UserDAOImpl() {
        try {
            userList = readAllUsers();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public static UserDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean signIn(String login, String password) {
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

    @Override
    public void register(User user) throws DAOException {
        if (user == null) throw new DAOException("Please create the user!");
        for (User u : userList) {
            if (user.getLogin().equals(u.getLogin())) throw new DAOException("This login has already been taken");
        }
        user.setId(generateID());
        userList.add(user);
        updateUsersFile(userList);
    }

    @Override
    public boolean deleteAllUsers() throws DAOException {
        userList = new ArrayList<>();
        updateUsersFile(userList);
        return true;
    }

    @Override
    public boolean deleteUser(String login) throws DAOException {
        if (login == null) throw new DAOException("The user to delete cannot be null");
        User user;
        Iterator<User> it = userList.iterator();
        while (it.hasNext()) {
            user = it.next();
            if (user.getLogin().equals(login)) {
                it.remove();
                updateUsersFile(userList);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers(){
        return userList;
    }

    @Override
    public String getUserByLogin(String userLogin) throws DAOException {
        String s = "";
        for (User user: userList) {
            if (user.getLogin().equals(userLogin)){
                s = user.toString();
            }
            else throw new DAOException("There is no such User");
        }
        return s;
    }



    //вспомогательные методы
    private int generateID() {
        int id = 1;
        for (User user : userList) {
            if (user.getId() >= id)
                id = user.getId() + 1;
        }
        return id;
    }

    private void updateUsersFile(List<User> userList) throws DAOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (User user: userList) {
                bw.write(user.toString());
                bw.write("\n");
            }
        } catch (IOException e) {
            throw new DAOException("Please check the file path");
        }
    }

    private List<User> readAllUsers () throws DAOException {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = reader.readLine()) != null) {
                User user = User.initializeUser(str);
                userList.add(user);
            }
        } catch (IOException e) {
            throw new DAOException("File with users does not exist");
        }
        return userList;

    }
}
