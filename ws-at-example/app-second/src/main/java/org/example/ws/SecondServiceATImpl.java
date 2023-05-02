package org.example.ws;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@WebService(serviceName = "SecondService",
        portName = "SecondPort",
        targetNamespace = "http://org.example.ws/",
        endpointInterface = "org.example.ws.SecondServiceAT")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class SecondServiceATImpl implements SecondServiceAT {

    private static final long ENTITY_ID = 1;

    @PersistenceContext()
    EntityManager em;

    @Override
    public long incrementAndGet() {
        AccessCounterForSecond counter = em.find(AccessCounterForSecond.class, ENTITY_ID);
        if (counter == null) {
            counter = new AccessCounterForSecond(ENTITY_ID);
            em.persist(counter);
        }
        counter.increment();
        return counter.getAccessCount();
    }
}
