package org.jboss.ws.booksearch;

public class Book {
    private String isbn13;
    private String title;
    private String author;

    public static Book of(String isbn13, String title, String author) {
        Book newOne = new Book();
        newOne.isbn13 = isbn13;
        newOne.title = title;
        newOne.author = author;
        return newOne;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
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

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
