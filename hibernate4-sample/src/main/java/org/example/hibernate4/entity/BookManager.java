package org.example.hibernate4.entity;

import org.example.hibernate4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.stream.IntStream;

@Singleton
@Startup
public class BookManager {
//    @PostConstruct
    public void prefill() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            IntStream.range(0, 5)
                    .forEach(i -> session.persist(Book.of(i, "test title " + i, "test author " + i)));
//            session.flush();
            tx.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
