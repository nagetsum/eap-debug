package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/load_prop")
public class LoadPropertyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/plain;charset=utf-8");

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties")) {
            Properties props = new Properties();
            props.load(is);
            res.getWriter().println("my.properties: key=msg, value=" + props.getProperty("msg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
