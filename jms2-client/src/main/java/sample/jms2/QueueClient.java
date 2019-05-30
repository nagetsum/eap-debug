package sample.jms2;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.Optional;

@ApplicationScoped
public class QueueClient {

    @Resource
    private ConnectionFactory cf;

    @Resource(lookup = "java:/jms/queue/HelloQueue")
    private Queue queue;

    public void send(String message) {
        try (JMSContext context = cf.createContext()) {
            context.createProducer().send(queue, message);
        }
    }

    public Optional<String> consume() {
        try (JMSContext context = cf.createContext()) {
            JMSConsumer consumer = context.createConsumer(queue);
            return Optional.ofNullable(consumer.receiveBodyNoWait(String.class));
        }
    }
}
