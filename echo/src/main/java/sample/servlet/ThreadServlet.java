package sample.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/thread")
public class ThreadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        int thread = Integer.valueOf(req.getParameter("threads"));

        for (int i = 0; i < thread; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("start thread");
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("finish thread");
                }
            }, "my-thread-" + i).start();
        }

        res.setContentType("text/plain;charset=utf-8");
        res.getWriter().println(thread + " threads created");
    }
}
