package by.javatr.dao.impl;

import by.javatr.dao.BookDAO;
import by.javatr.exception.DAOException;
import by.javatr.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private static final String fileName = "E:\\Epam trainings\\Introduction to Java\\LibraryYuntsevich\\txt\\books.txt";

    @Override
    public boolean addBook(Book book) throws DAOException {
        List<Book> bookList;
        try {
            bookList = readAllBooks();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        for (Book b : bookList) {
            if (book.getTitle().equals(b.getTitle()) && book.getAuthor().equals(b.getAuthor())) {
                throw new DAOException("This book is already in library");
            }
        }
        book.setId(generateID(bookList));
        bookList.add(book);
        try {
            updateBooksFile(bookList);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteBookByID(String id) throws DAOException {
        Book book;
        List<Book> bookList;
        try {
            bookList = readAllBooks();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        Iterator<Book> it = bookList.iterator();
        while (it.hasNext()) {
            book = it.next();
            String bookId = "" + book.getId();
            if (bookId.equals(id)) {
                it.remove();
                try {
                    updateBooksFile(bookList);
                } catch (IOException e) {
                    throw new DAOException(e);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() throws DAOException {
        List<Book> bookList;
        try {
            bookList = readAllBooks();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        return bookList;
    }

    @Override
    public String getBookByTitle(String title) throws DAOException {
        String str = "";
        List<Book> bookList;
        try {
            bookList = readAllBooks();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                str = book.toString();
            }
        }
        return str;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) throws DAOException {
        List<Book> booksByAuthor = new ArrayList<>();
        List<Book> bookList;
        try {
            bookList = readAllBooks();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }


    //вспомогательные методы
    private List<Book> readAllBooks() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        return ((ArrayList<Book>) ois.readObject());
    }

//    private Book initializeBook(String str) {
//        Book book = new Book();
//        if (str != null) {
//            String[] parts = str.split(" ");
//            // if (parts.length == 4) {
//            for (String part : parts) {
//                if (part != null) {
//                    if (part.contains("id=")) {
//                        String id = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        book.setId(Integer.parseInt(id));
//                    }
//                    if (part.contains("title=")) {
//                        String title = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        book.setTitle(title);
//                    }
//                    if (part.contains("author=")) {
//                        String author = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        book.setAuthor(author);
//                    }
//                    if (part.contains("pages=")) {
//                        String pages = part.substring(part.indexOf("=") + 1, part.length() - 1);
//                        book.setPages(Integer.parseInt(pages));
//                    }
//
//                }
//            }
//        }
//        //    }
//        return book;
//    }

    private void updateBooksFile(List<Book> bookList) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(bookList);
    }

    private int generateID(List<Book> bookList) {
        int id = 1;
        for (Book book : bookList) {
            if (book.getId() >= id) {
                id = book.getId() + 1;
            }
        }
        return id;
    }

}
