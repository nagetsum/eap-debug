package org.example.soap.client;

import org.example.soap.server.EchoWebService;
import org.example.soap.server.EchoWebServiceService;
import org.example.soap.server.RequestMessage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class CallSoapServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        res.setContentType("text/plain; charset=utf-8");
        EchoWebServiceService service = new EchoWebServiceService();
        EchoWebService echoWebService =  service.getEchoWebServicePort();

        // Client options in request context
//        ((BindingProvider) echoWebService).getRequestContext().put("javax.xml.ws.client.connectionTimeout", "30000");
//        ((BindingProvider) echoWebService).getRequestContext().put("javax.xml.ws.client.receiveTimeout", "60000");

        // Client option by CXF API
//        Client client = ClientProxy.getClient(port);
//        HTTPConduit http = (HTTPConduit) client.getConduit();
//        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
//        httpClientPolicy.setConnectionTimeout(10000); // millisecond
//        httpClientPolicy.setReceiveTimeout(30000);    // millisecond
//        httpClientPolicy.setAllowChunking(false);     // true by default
//        http.setClient(httpClientPolicy);

        RequestMessage msg = new RequestMessage();
        msg.setId(1);
        msg.setMessage("Hello, world!");

        res.getWriter().write(echoWebService.echo(msg).toString());
    }
}