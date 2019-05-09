package sample.servlet;

import sample.ejb.AccountTransfer;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

    @EJB
    private AccountTransfer accountTransfer;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        accountTransfer.transfer(1, 2, 100);

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("transfer 100-yen from accountId 1 to 2 done.");
    }
}
