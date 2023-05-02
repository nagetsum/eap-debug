package org.example.ws;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class FirstService {

    private static final long ENTITY_ID = 1;

    @PersistenceContext()
    EntityManager em;

    public long incrementAndGet() {
        AccessCounterForFirst counter = em.find(AccessCounterForFirst.class, ENTITY_ID);
        if (counter == null) {
            counter = new AccessCounterForFirst(ENTITY_ID);
            em.persist(counter);
        }
        counter.increment();
        return counter.getAccessCount();
    }
}
