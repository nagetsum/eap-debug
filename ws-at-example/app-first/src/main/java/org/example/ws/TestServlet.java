package org.example.ws;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @EJB
    CountupService countupService;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            countupService.incrementWithXA();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/plain; charset=utf-8");
        resp.getWriter().println("check Databases");
    }

}
