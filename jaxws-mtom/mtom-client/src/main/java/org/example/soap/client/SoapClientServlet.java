package org.example.soap.client;

import org.example.soap.server.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/client")
public class SoapClientServlet extends HttpServlet {
    private static final String FILEPATH_TO_SEND = "/home/nagetsum/duke.jpg";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/plain; charset=utf-8");

        // Option1. using JAX-WS client stub code generated by wsimport command
//        FileReceiverServiceService service = new FileReceiverServiceService();
//        FileReceiverService fileReceiverService = service.getFileReceiverServicePort();
//        SOAPBinding binding = (SOAPBinding) ((BindingProvider) fileReceiverService).getBinding();
//        binding.setMTOMEnabled(true);
//
//        DataHandler dh = new DataHandler(new FileDataSource(FILEPATH_TO_SEND));
//        String result = fileReceiverService.uploadFile(dh, "duke.jpg");
//        res.getWriter().println("result: " + result);

        try {
            // Option2(advanced). using javax.xml.ws.Dispatch interface
            sendRequest(res.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(PrintWriter out) throws Exception {
        URL wsdlUrl = new URL("http://127.0.0.1:8080/mtom-server/FileReceiverService?wsdl");
        QName serviceName = new QName("http://server.soap.example.org/", "FileReceiverServiceService");
        QName portName = new QName("http://server.soap.example.org/", "FileReceiverServicePort");

        Service service = Service.create(wsdlUrl, serviceName);
        JAXBContext jbc = JAXBContext.newInstance("org.example.soap.server");
        Dispatch<Object> dispatch = service.createDispatch(portName, jbc, Service.Mode.PAYLOAD);

        // enable MTOM on client side
        SOAPBinding binding = (SOAPBinding) dispatch.getBinding();
        binding.setMTOMEnabled(true);

        UploadFile uploadFile = new UploadFile();
        uploadFile.setArg0(new DataHandler(new FileDataSource(FILEPATH_TO_SEND)));
        uploadFile.setArg1("duke.png");
        ObjectFactory factory = new ObjectFactory();
        JAXBElement<UploadFile> request = factory.createUploadFile(uploadFile);

        JAXBElement<UploadFileResponse> response = (JAXBElement<UploadFileResponse>) dispatch.invoke(request);
        out.println("result:" + response.getValue().getReturn());
    }
}
