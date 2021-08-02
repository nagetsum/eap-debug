package org.example.log4j.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EchoServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {

        LOG.info("Hello, world!");
        res.setContentType("text/plain; charset=UTF-8");
        res.getWriter().write("echo");
    }
}
