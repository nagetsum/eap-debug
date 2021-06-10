package org.example.jms.spring.service;

public interface JmsQueueSender {
    void sendMessage(String message);
}
