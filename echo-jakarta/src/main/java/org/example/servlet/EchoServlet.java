package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        System.out.println("### org.example.servlet.EchoServlet doGet called");
        resp.setContentType("text/plain;charset=utf-8");
        resp.getWriter().println("echo");
    }
}
