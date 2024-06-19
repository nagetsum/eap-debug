package org.example.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Book.find", query = "SELECT b FROM Book b WHERE b.id = :id")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private String author;

    public static Book of(String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

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
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}