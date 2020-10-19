package org.example.hibernate.ejb;

import org.example.hibernate.entity.Book;

import javax.ejb.Local;

@Local
public interface BookManager {
    Book findById(long id);
}
