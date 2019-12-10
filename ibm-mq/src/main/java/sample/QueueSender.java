package sample;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class QueueSender {

    @Resource(lookup = "java:jboss/IBM_MQ_CONNECTION_FACTORY")
    ConnectionFactory cf;

    @Resource(lookup = "java:jboss/DEV.QUEUE.1")
    Queue devQueue1;

    public void send(String message) {
        try (Connection conn = cf.createConnection();
             Session session = conn.createSession()) {
            MessageProducer mp = session.createProducer(devQueue1);
            Message msg = session.createTextMessage(message);
            mp.send(msg);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
