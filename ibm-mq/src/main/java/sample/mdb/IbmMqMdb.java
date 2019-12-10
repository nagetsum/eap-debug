package sample.mdb;

import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name="IbmMqMdb", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "hostName", propertyValue = "localhost"),
        @ActivationConfigProperty(propertyName = "port", propertyValue = "1414"),
        @ActivationConfigProperty(propertyName = "channel", propertyValue = "DEV.APP.SVRCONN"),
        @ActivationConfigProperty(propertyName = "queueManager", propertyValue = "QM1"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "DEV.QUEUE.1"),
        @ActivationConfigProperty(propertyName = "transportType", propertyValue = "CLIENT")
})
@ResourceAdapter(value = "wmq.jmsra.rar")
public class IbmMqMdb implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("consumed: " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
