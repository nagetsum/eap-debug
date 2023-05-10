package org.example.jms;

import org.example.jms.producer.MessageProducer;
import org.example.jms.producer.MessageProducerHome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send_message")
public class SendMessageServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SendMessageServlet.class);

    @EJB
    MessageProducerHome home;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String message = req.getParameter("message");
        if (message == null || "".equals(message)) {
            resp.setStatus(400);
            LOG.warn("query string message is required in this servlet path");
            return;
        }

        try {
            MessageProducer producer = home.create();
            producer.send(message);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/plain; charset=utf-8");
        resp.getWriter().println("message is sent to java:/jms/queue/HelloQueue");
    }
}
