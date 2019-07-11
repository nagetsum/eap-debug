package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession();
        if (session.isNew()) {
            session.setAttribute("cnt", new AtomicInteger());
        }
        AtomicInteger cnt = (AtomicInteger) session.getAttribute("cnt");
        int now = cnt.incrementAndGet();
        session.setAttribute("cnt", cnt);

        res.setContentType("text/plain;charset=utf-8");
        res.getWriter().write("count = " + now);
    }
}
