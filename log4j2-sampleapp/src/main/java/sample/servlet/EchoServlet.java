package sample.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(EchoServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        LOG.info("test log4j message");
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("echo");
    }
}
