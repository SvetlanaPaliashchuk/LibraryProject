package by.javatr.dao.impl;

import by.javatr.dao.IBookDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.entity.Book;
import by.javatr.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements IBookDAO {

    private static final String fileName = "E:\\LibraryYuntsevich\\src\\by\\javatr\\IOData\\books.txt";

    private static final BookDAOImpl INSTANCE = new BookDAOImpl();

    private BookDAOImpl() {
    }

    public static BookDAOImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public boolean addBook(Book book) throws DAOException {
        if (book == null) throw new DAOException("Please create the book!");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains("title=" + book.getTitle() + ", ") && str.contains("author=" + book.getAuthor() + ", "))
                    throw new DAOException("The book with these parameters has already been added");
            }
            reader.close();
            book.setId(generateID());
            bw.write(book.toString());
            bw.write("\n");
        } catch (IOException e) {
            throw new DAOException("Please check the file path");
        }
        return true;
    }

    @Override
    public boolean deleteBook(String id) throws DAOException {
        if (id == null) throw new DAOException("The book id cannot be null");
        boolean isDeleted = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> list = new ArrayList<>();
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains("id=" + id + ",")) isDeleted = true;
                if (!str.contains("id=" + id + ",")) {
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
        return isDeleted;
    }

    @Override
    public List<String> getAllBooks() throws DAOException {
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
    public String getBookByTitle(String title) throws DAOException {
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((str = reader.readLine()) != null) {
                if (str.contains("title=" + title)) return str;
            }
        } catch (IOException e) {
            throw new DAOException("There is no such book");
        }
        return str;
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
