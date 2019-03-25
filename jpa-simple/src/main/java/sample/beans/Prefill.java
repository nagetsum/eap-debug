package sample.beans;

import sample.entity.Book;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class Prefill {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    void init() {
        Book effectiveJava = Book.of("Effective Java", "Joshua Bloch");
        em.persist(effectiveJava);
        Book javaConcurrency = Book.of("Java Concurrency in Practice", "Brian Goetz");
        em.persist(javaConcurrency);
    }
}
