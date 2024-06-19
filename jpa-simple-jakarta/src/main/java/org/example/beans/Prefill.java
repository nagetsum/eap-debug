package org.example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.example.entity.Book;

@Singleton
@Startup
public class Prefill {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    void init() {
        // insert 2 books
        Book effectiveJava = Book.of("Effective Java", "Joshua Bloch");
        em.persist(effectiveJava);
        Book javaConcurrency = Book.of("Java Concurrency in Practice", "Brian Goetz");
        em.persist(javaConcurrency);
    }
}