package org.example.ws;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Remote
@WebService(targetNamespace = "http://org.example.ws/", name = "Second")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SecondServiceAT {
    @WebMethod
    public long incrementAndGet();
}