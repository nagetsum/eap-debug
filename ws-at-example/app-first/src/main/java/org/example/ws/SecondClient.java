package org.example.ws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class SecondClient {
    private static SecondServiceAT cached;
    public static synchronized SecondServiceAT client() throws Exception {
        if (cached != null) {
            return cached;
        }

        URL wsdlLocation = new URL("http://localhost:8180/app-second-0.1/SecondService/SecondServiceATImpl?wsdl");
        QName serviceName = new QName("http://org.example.ws/", "SecondService");
        QName portName = new QName("http://org.example.ws/", "SecondPort");

        Service service = Service.create(wsdlLocation, serviceName);
        cached = service.getPort(portName, SecondServiceAT.class);

        return cached;
    }
}
