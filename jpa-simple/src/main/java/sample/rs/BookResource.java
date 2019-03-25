package sample.rs;

import sample.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/books")
public class BookResource {

    @PersistenceContext
    private EntityManager entityManager;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Book get(@PathParam("id") long id) {
        return entityManager.find(Book.class, id);

//        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
    }
}
