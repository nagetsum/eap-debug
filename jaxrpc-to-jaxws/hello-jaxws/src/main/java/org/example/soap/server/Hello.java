package org.example.soap.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 3.3.7.redhat-00001
 * 2021-09-11T22:41:10.893+09:00
 * Generated source version: 3.3.7.redhat-00001
 *
 */
@WebService(targetNamespace = "http://hello.ws.jboss.org/", name = "Hello")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {

    @WebMethod
    @WebResult(name = "result", targetNamespace = "http://hello.ws.jboss.org/", partName = "result")
    public java.lang.String hello(

        @WebParam(partName = "String_1", name = "String_1")
        java.lang.String string1
    );
}
