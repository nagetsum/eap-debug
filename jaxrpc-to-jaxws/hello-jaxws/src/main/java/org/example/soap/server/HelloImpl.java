package org.example.soap.server;

import javax.jws.WebService;

@WebService(serviceName="HelloService",
        portName = "HelloPort",
        targetNamespace = "http://hello.ws.jboss.org/",
        endpointInterface = "org.example.soap.server.Hello")
public class HelloImpl implements Hello {
    @Override
    public String hello(String name) {
        return "Hello JAX-WS " + name + "!";
    }
}
