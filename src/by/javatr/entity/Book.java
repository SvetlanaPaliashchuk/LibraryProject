package by.javatr.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = -300386522713785194L;


    private int id;
    private String title;
    private String author;
    private int pages;


    public Book() {
    }

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + pages;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        return (id == other.getId()) && ((title != null && title.equals(other.getTitle())))
                && ((author != null && author.equals(other.getAuthor())))
                && (pages == other.getPages());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", title=" + title + ", author=" + author + ", pages=" + pages + "]";
    }
}
