package org.example.hibernate.ejb;

import javax.ejb.Stateless;

import org.example.hibernate.entity.Book;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;

@Stateless
public class BookManagerImpl implements BookManager {
    public Book findById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Book managed = (Book) session.load(Book.class, id);
            Book result = new Book(managed.getId(), managed.getTitle(), managed.getAuthor());
            return result;
        } finally {
            session.close();
        }
    }
}
