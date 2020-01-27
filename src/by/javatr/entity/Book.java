package by.javatr.entity;

public class Book {
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


    public static Book initializeBook(String str) {
        Book book = new Book();
        if (str != null) {
            String[] parts = str.split(" ");
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
        return book;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", title=" + title + ", author=" + author + ", pages=" + pages + "]";
    }
}
