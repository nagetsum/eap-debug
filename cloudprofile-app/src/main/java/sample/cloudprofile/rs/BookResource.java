package sample.cloudprofile.rs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import sample.cloudprofile.entity.Book;

@Path("/books")
public class BookResource {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Book get(@PathParam("id") long id) {
        return entityManager.find(Book.class, id);
    }
}
