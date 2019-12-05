package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/slow")
public class SlowServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        int timeout = Integer.parseInt(req.getParameter("timeout"));
        try {
            System.out.println("SLEEP " + timeout + " millisec start");
            Thread.sleep(timeout);
            System.out.println("SLEEP " + timeout + " millisec finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        res.setContentType("text/plain;charset=utf-8");
        res.getWriter().write("done.");
    }
}
