package org.example.hibernate.servlet;

import org.example.hibernate.ejb.BookManager;
import org.example.hibernate.entity.Book;
import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindBookServlet extends HttpServlet {

    @EJB
    BookManager bookManager;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        Book book = bookManager.findById(1);
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().write(book.toString());
    }
}