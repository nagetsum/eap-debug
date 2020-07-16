package org.example.sastruts.test.dto;

public class BookDto {
    public long id;
    public String title;

    @Override
    public String toString() {
        return "id=" + id + ", title=" + title;
    }
}
