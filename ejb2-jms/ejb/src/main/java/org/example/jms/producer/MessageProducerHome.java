package org.example.jms.producer;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface MessageProducerHome extends EJBLocalHome {
    public MessageProducer create() throws CreateException;
}
