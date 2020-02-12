package by.javatr.dao.impl;

import by.javatr.dao.UserDAO;
import by.javatr.exception.DAOException;
import by.javatr.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String fileName = "E:\\Epam trainings\\Introduction to Java\\LibraryYuntsevich\\txt\\users.txt";

    @Override
    public boolean signIn(String login, String password) throws DAOException {
        List<User> userList;
        try {
            userList = readAllUsers();
        } catch (IOException |ClassNotFoundException e) {
            throw new DAOException(e);
        }
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(User user) throws DAOException {
        List<User> userList;
        try {
            userList = readAllUsers();
        } catch (IOException |ClassNotFoundException e) {
            throw new DAOException(e);
        }
        for (User u : userList) {
            if (user.getLogin().equals(u.getLogin())) {
                throw new DAOException("This login has already been taken");
            }
        }
        user.setId(generateID(userList));
        userList.add(user);
        try {
            updateUsersFile(userList);
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteAllUsers() throws DAOException {
        List<User> userList = new ArrayList<>();
        try {
            updateUsersFile(userList);
            return true;
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteUserByLogin(String login) throws DAOException {
        User user;
        List<User> userList;
        try {
            userList = readAllUsers();
        } catch (IOException |ClassNotFoundException e) {
            throw new DAOException(e);
        }
        Iterator<User> it = userList.iterator();
        while (it.hasNext()) {
            user = it.next();
            if (user.getLogin().equals(login)) {
                it.remove();
                try {
                    updateUsersFile(userList);
                    return true;
                } catch (IOException e) {
                    throw new DAOException(e);
                }
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> userList;
        try {
            userList = readAllUsers();
        } catch (IOException |ClassNotFoundException e) {
            throw new DAOException(e);
        }
        return userList;
    }

    @Override
    public String getUserByLogin(String userLogin) throws DAOException {
        String s = "";
        List<User> userList;
        try {
            userList = readAllUsers();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        for (User user : userList) {
            if (user.getLogin().equals(userLogin)) {
                return user.toString();
            }
        }
        return s;
    }

    //вспомогательные методы
    private int generateID(List<User> userList) {
        int id = 1;
        for (User user : userList) {
            if (user.getId() >= id) {
                id = user.getId() + 1;
            }
        }
        return id;
    }

    private void updateUsersFile(List<User> userList) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(userList);
    }

    private List<User> readAllUsers() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        return ((ArrayList<User>) ois.readObject());
    }

//    private User initializeUser(String str) {
//        User user = new User();
//        if (str != null) {
//            String[] parts = str.split(" ");
//            // if (parts.length == 6) {
//            for (String part : parts) {
//                if (part != null) {
//                    if (part.contains("id=")) {
//                        String id = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        user.setId(Integer.parseInt(id));
//                    }
//                    if (part.contains("firstName=")) {
//                        String name = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        user.setFirstName(name);
//                    }
//                    if (part.contains("surname=")) {
//                        String surname = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        user.setSurname(surname);
//                    }
//                    if (part.contains("login=")) {
//                        String login = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        user.setLogin(login);
//                    }
//                    if (part.contains("password=")) {
//                        String password = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        user.setPassword(password);
//                    }
//                    if (part.contains("role=")) {
//                        String role = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        if (role.equals(Role.READER.toString()))
//                            user.setRole(Role.READER);
//                        else user.setRole(Role.ADMIN);
//                    }
//                }
//            }
//        }
    //   }
//        return user;
//    }
}
