package by.javatr.dao.impl;

import by.javatr.dao.IBookDAO;
import by.javatr.dao.exception.DAOException;
import by.javatr.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDAOImpl implements IBookDAO {

    private static final String fileName = "E:\\LibraryYuntsevich\\src\\by\\javatr\\IOData\\books.txt";

    private static final BookDAOImpl INSTANCE = new BookDAOImpl();

    private List<Book> bookList;

    private BookDAOImpl() {
        try {
            bookList = readAllBooks();
        } catch (DAOException e) {
            bookList = null;
        }
    }

    public static BookDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        for (Book b : bookList) {
            if (book.getTitle().equals(b.getTitle()) && book.getAuthor().equals(b.getAuthor()))
                throw new DAOException("This book is already in library");
        }
        book.setId(generateID());
        bookList.add(book);
        updateBooksFile(bookList);
        return true;
    }

    @Override
    public boolean deleteBook(String id) throws DAOException {
        Book book;
        Iterator<Book> it = bookList.iterator();
        while (it.hasNext()) {
            book = it.next();
            String bookId = "" + book.getId();
            if (bookId.equals(id)) {
                it.remove();
                updateBooksFile(bookList);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookList;
    }

    @Override
    public String getBookByTitle(String title){
        String str = "";
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                str = book.toString();
            }
        }
        return str;
    }

    @Override
    public List<Book> getBooksByAuthor(String author){
        List<Book> booksOfAuthor = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author))
                booksOfAuthor.add(book);
        }
        return booksOfAuthor;
    }


    //вспомогательные методы
    private List<Book> readAllBooks() throws DAOException {
        List<Book> bookList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = reader.readLine()) != null) {
                Book book = initializeBook(str);
                bookList.add(book);
            }
        } catch (IOException e) {
            throw new DAOException("File with books does not exist");
        }
        return bookList;
    }

    private Book initializeBook(String str) {
        Book book = new Book();
        if (str != null) {
            String[] parts = str.split(" ");
            // if (parts.length == 4) {
            for (String part : parts) {
                if (part != null) {
                    if (part.contains("id=")) {
                        String id = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        book.setId(Integer.parseInt(id));
                    }
                    if (part.contains("title=")) {
                        String title = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        book.setTitle(title);
                    }
                    if (part.contains("author=")) {
                        String author = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        book.setAuthor(author);
                    }
                    if (part.contains("pages=")) {
                        String pages = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        book.setPages(Integer.parseInt(pages));
                    }

                }
            }
        }
        //    }
        return book;
    }

    private void updateBooksFile(List<Book> bookList) throws DAOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Book book : bookList) {
                bw.write(book.toString());
                bw.write("\n");
            }
        } catch (IOException e) {
            throw new DAOException("Please check the file path");
        }
    }

    private int generateID() {
        int id = 1;
        for (Book book : bookList) {
            if (book.getId() >= id)
                id = book.getId() + 1;
        }
        return id;
    }

}
