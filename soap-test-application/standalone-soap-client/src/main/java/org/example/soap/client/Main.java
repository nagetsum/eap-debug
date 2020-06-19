package org.example.soap.client;

import org.example.soap.server.EchoWebService;
import org.example.soap.server.EchoWebServiceService;
import org.example.soap.server.RequestMessage;

public class Main {
    public static void main(String[] args) {
        EchoWebServiceService service = new EchoWebServiceService();
        EchoWebService echoService = service.getEchoWebServicePort();

        RequestMessage req = new RequestMessage();
        req.setId(1);
        req.setMessage("Hello, world!");
        echoService.echo(req);

        System.out.println("SOAP request is done");
    }
}
