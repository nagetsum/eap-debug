package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/count")
public class CounterServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        HttpSession session = req.getSession();
        if (session.isNew()) {
            session.setAttribute("counter", new AtomicInteger());
        }

        AtomicInteger counter = (AtomicInteger) session.getAttribute("counter");
        int now = counter.incrementAndGet();
        session.setAttribute("counter", counter);

        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().write("count: " + now + ", host: " + InetAddress.getLocalHost().getHostName());
    }
}
