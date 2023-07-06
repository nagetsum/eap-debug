package com.example.servlet;

import com.baeldung.jni.HelloWorldJNI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {

        HelloWorldJNI.sayHello();

        resp.setContentType("text/plain; charset=utf-8");
        resp.getWriter().println("see console");
    }
}
