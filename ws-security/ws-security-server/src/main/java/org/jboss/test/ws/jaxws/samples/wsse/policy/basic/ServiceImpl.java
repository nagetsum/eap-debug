package org.jboss.test.ws.jaxws.samples.wsse.policy.basic;

import org.jboss.ws.api.annotation.EndpointConfig;

import javax.jws.WebService;

@WebService(
        portName = "SecurityServicePort",
        serviceName = "SecurityService",
        wsdlLocation = "WEB-INF/wsdl/SecurityService.wsdl",
        targetNamespace = "http://www.jboss.org/jbossws/ws-extensions/wssecuritypolicy",
        endpointInterface = "org.jboss.test.ws.jaxws.samples.wsse.policy.basic.ServiceIface"
 )
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
public class ServiceImpl implements ServiceIface {
    @Override
    public String sayHello() {
        return "Secure Hello World!";
    }
}
