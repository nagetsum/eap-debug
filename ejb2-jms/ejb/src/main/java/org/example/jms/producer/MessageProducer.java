package org.example.jms.producer;

import javax.ejb.EJBLocalObject;

public interface MessageProducer extends EJBLocalObject {
    public void send(String message);
}
