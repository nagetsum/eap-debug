package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// secured url. This method only allows for admin role.
@WebServlet("/admin/show_username")
public class AdminServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("Welcome! " + req.getRemoteUser());
    }
}
