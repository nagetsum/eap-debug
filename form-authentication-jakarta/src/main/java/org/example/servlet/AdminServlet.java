package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// secured url. This method only allows for admin role.
@WebServlet("/admin/show_username")
public class AdminServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("Welcome! " + req.getRemoteUser());

        boolean logout = Boolean.valueOf(req.getParameter("logout"));
        if (logout) {
//            req.getSession().invalidate();
            req.logout();
            System.out.println("logout");
        }
    }
}
