package org.example.jms.spring.service;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsQueueSenderImpl implements JmsQueueSender {

    JmsMessagingTemplate jmsMessagingTemplate;

    public JmsQueueSenderImpl(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public void sendMessage(String message) {
        jmsMessagingTemplate.convertAndSend(message);
    }
}
