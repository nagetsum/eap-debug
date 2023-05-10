package org.example.jms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Simple MDB implementation based on EJB2 interfaces with ejb-jar.xml
 */
public class HelloMDB implements MessageDrivenBean, MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(HelloMDB.class);
    private MessageDrivenContext context;  // unused

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            LOG.info("Start MDB: Hello, " + textMessage.getText());
            sleep(30000);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        LOG.info("Finished MDB");
    }

    private void sleep(long sleepMillisecond) {
        try {
            Thread.sleep(sleepMillisecond);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void setMessageDrivenContext(MessageDrivenContext context) throws EJBException {
        this.context = context;
    }

    @Override
    public void ejbRemove() throws EJBException {
    }
}
