package org.example.jaxrpc.client.servlet;

import org.jboss.ws.hello.Hello;
import org.jboss.ws.hello.HelloService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class ClientServlet extends HttpServlet {

    private Hello helloPort;

    @Override
    public void init() {
        HelloService service = new HelloService();
        this.helloPort = service.getHelloPort();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/plain;charset=utf-8");
        res.getWriter().println(helloPort.hello("test client"));
    }
}
