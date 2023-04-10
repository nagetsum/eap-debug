package org.example.hibernate4.rs;

import org.example.hibernate4.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.example.hibernate4.util.HibernateUtil;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.IntStream;

@Path("/books")
public class BookResource {
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book get(@PathParam("id") long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Book result = (Book) session.get(Book.class, id);
            return Book.of(result.getId(), result.getTitle(), result.getAuthor());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Path("/all")
    @POST
    public Response addAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            IntStream.range(0, 5)
                    .forEach(i -> session.persist(Book.of(i, "test title " + i, "test author " + i)));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return Response.ok().build();
    }

    @Path("/all")
    @DELETE
    public Response deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete Book b where b.id = :id";
        for (long l = 0; l < 5; l++) {
//            int deletedRecordsCount = session.createQuery(hql)
//                    .setLong("id", l)
//                    .executeUpdate();
            Book b = (Book) session.get(Book.class, l);
            session.delete(b);
        }

        tx.commit();
        session.close();

        return Response.ok().build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete Book b where b.id = :id";
        int deletedRecordsCount = session.createQuery(hql)
                .setLong("id", id)
                .executeUpdate();

        try {
            System.out.println("sleep 10 secs start");
            Thread.sleep(10000);
            System.out.println("sleep 10 secs finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tx.commit();
        session.close();

        return Response.ok().build();
    }
}
