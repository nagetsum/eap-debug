package sample.cloudprofile.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import sample.cloudprofile.entity.Book;

@ApplicationScoped
public class Prefill {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
        Book effectiveJava = Book.of("Effective Java", "Joshua Bloch");
        em.persist(effectiveJava);
        Book javaConcurrency = Book.of("Java Concurrency in Practice", "Brian Goetz");
        em.persist(javaConcurrency);
    }
}