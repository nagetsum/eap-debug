package sample.servlet;

import sample.service.InventoryManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/kick")
public class KickServlet  extends HttpServlet {

    @EJB
    InventoryManager inventoryManager;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {

        inventoryManager.search(100);

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("done.");
    }
}
