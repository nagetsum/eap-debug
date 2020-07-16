package org.example.axis2.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "org.example.axis2.webservice.Echo",
        serviceName = "EchoService", portName = "EchoServicePort", wsdlLocation = "WEB-INF/wsdl/EchoService.wsdl")
public class EchoService implements Echo {
    @Override
    public String echo(String s) {
        return "echo: " + s;
    }
}
