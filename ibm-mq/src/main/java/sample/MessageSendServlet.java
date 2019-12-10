package sample;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@WebServlet("/send")
public class MessageSendServlet extends HttpServlet {

    private static final AtomicLong count = new AtomicLong();

    @Inject
    QueueSender queueSender;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        queueSender.send("message " + count.incrementAndGet());
        res.setContentType("text/plain;charset=utf-8");
        res.getWriter().println("send : message " + count.get());
    }
}