package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exception")
public class ExceptionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        throw new RuntimeException("Wrong error");
    }
}
