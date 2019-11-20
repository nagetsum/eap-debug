package sample.servlet;

import sample.ejb.CounterBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class SampleServlet extends HttpServlet {

    @EJB
    private CounterBean bean;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        int now = bean.incrementAndGet();
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().print(now);
    }
}
