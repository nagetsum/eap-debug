package org.example.jms.spring.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsQueueListner {
    @JmsListener(destination = "java:/jms/queue/HelloQueue")
    public void processMessage(String message) {
        System.out.println("message = " + message);
    }
}
