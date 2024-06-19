package org.example.rs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.example.entity.Book;

@Path("/books")
@ApplicationScoped
public class BookResource {

    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Book get(@PathParam("id") long id) {
        Book b = entityManager.find(Book.class, id);
        Book result = new Book();
        result.setId(b.getId());
        result.setTitle(b.getTitle());
        result.setAuthor(b.getAuthor());
        return result;
    }
}
