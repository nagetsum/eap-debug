package org.example.jms.producer;

import javax.annotation.Resource;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class MessageProducerBean implements SessionBean {
    private ConnectionFactory cf;
    private Queue queue;

    private SessionContext sessionContext;  // unused

    public void send(String message) {
        try (JMSContext context = cf.createContext()) {
            context.createProducer().send(queue, message);
        }
    }

    public void ejbCreate() throws CreateException {
        try {
            this.cf = InitialContext.doLookup("java:/JmsXA");
            this.queue = InitialContext.doLookup("java:/jms/queue/HelloQueue");
        } catch (NamingException e) {
            throw new CreateException("lookup failed: " + e.getExplanation());
        }
    }
    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
        this.sessionContext = sessionContext;
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
    }
}
