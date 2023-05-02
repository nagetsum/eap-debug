package org.example.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class CountupService {

    @EJB
    FirstService firstService;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void incrementWithXA() throws Exception {
        // local EJB
        firstService.incrementAndGet();

        // SOAP service with WS-AT
        SecondServiceAT secondServiceAT = SecondClient.client();
        secondServiceAT.incrementAndGet();
    }
}
