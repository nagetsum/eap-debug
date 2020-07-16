package org.example.axis2.client;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import java.io.IOException;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        res.setContentType("text/plain; charset=utf-8");

        EchoService_Service service = new EchoService_Service();
        EchoService echo = service.getEchoServicePort();
        res.getWriter().println(echo.echo("Hello,world"));
    }
}
