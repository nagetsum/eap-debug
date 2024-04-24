package org.example.spring.session.demo.servlet;

import org.springframework.web.util.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/count")
public class SessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        // TODO: fix required. This code is NOT thread safe. HttpSessionMutexListener is not called with Spring Session. 
        Object mutex = WebUtils.getSessionMutex(session);
        synchronized (mutex) {
            requestHandle(req, resp, session);
        }
    }

    private void requestHandle(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        if (session.isNew()) {
            session.setAttribute("count", 0);
        }

        int count = (Integer) session.getAttribute("count");
        count++;
        session.setAttribute("count", count);

        resp.setContentType("text/plain; charset=utf-8");
        resp.getWriter().println("count: " + count + ", id=" + session.getId());
    }
}
