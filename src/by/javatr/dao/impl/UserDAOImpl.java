package by.javatr.dao.impl;

import by.javatr.dao.IUserDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private static final String fileName = "E:\\LibraryYuntsevich\\src\\by\\javatr\\IOData\\users.txt";
    private static final UserDAOImpl INSTANCE = new UserDAOImpl();

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public void signIn(String login, String password) throws DAOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains(login) && str.contains(password)) return;
            }
        } catch (IOException e) {
            throw new DAOException("There is no such user, please, register");
        }
    }

    @Override
    public void register(User user) throws DAOException {
        if (user == null) throw new DAOException("Please create the user!");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains("login=" + user.getLogin() + ", "))
                    throw new DAOException("The user with these parameters has already been registered");
            }
            reader.close();
            user.setId(generateID());
            bw.write(user.toString());
            bw.write("\n");
        } catch (IOException e) {
            throw new DAOException("Please check the file path");
        }
    }

    @Override
    public boolean deleteAllUsers() throws DAOException {
        boolean isDeleted = false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("");
            isDeleted = true;
        } catch (Exception e) {
            throw new DAOException("Could not delete all users");
        }
        return isDeleted;
    }

    @Override
    public boolean deleteUser(String login) throws DAOException {
        if (login == null) throw new DAOException("The user to delete cannot be null");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> list = new ArrayList<>();
            String str;
            while ((str = reader.readLine()) != null) {
                if (!str.contains("login=" + login + ",")) {
                    list.add(str);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (String s : list) {
                bw.write(s);
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DAOException("Could not delete the user");
        }
        return true;
    }

    @Override
    public List<String> getAllUsers() throws DAOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = reader.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            throw new DAOException("Please check the file name");
        }

        return list;
    }

    @Override
    public String getUserByLogin(String userLogin) throws DAOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains("login=" + userLogin)) return str;
            }
        } catch (IOException e) {
            throw new DAOException("There is no such user");
        }
        return null;
    }

    private int generateID() throws DAOException {
        int count = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            if ((reader.readLine()) == null) return count;
            String str;
            while ((str = reader.readLine()) != null) {
                String[] parts = str.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].contains("id=")) {
                        String id = parts[i].substring(parts[i].indexOf("=") + 1, parts[i].length() - 1);
                        int number = Integer.parseInt(id);
                        if (number >= count) count = number + 1;
                    }
                }
            }

        } catch (IOException e) {
            throw new DAOException("Please check the file name");
        }
        return count;
    }
}
