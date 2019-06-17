package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.jboss.logging.Logger;


@WebServlet("/echo")
public class EchoServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(EchoServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().write("echo");
        System.out.println("test print");
        logger.info("test message");
    }
}
