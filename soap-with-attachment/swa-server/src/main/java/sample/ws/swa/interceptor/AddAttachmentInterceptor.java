package sample.ws.swa.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Attachment;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.io.*;
import java.util.*;

public class AddAttachmentInterceptor extends AbstractPhaseInterceptor<Message> {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final int EOF = -1;

    public AddAttachmentInterceptor() {
        // This interceptor should execute before org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor that is execute on Phase.PRE_LOGICAL
        // Because to set Content-Transfer-Encoding to MIME-attachment, it need to pass line 101 - 102 code in the following code.
        // see https://github.com/apache/cxf/blob/cxf-3.1.12/rt/bindings/soap/src/main/java/org/apache/cxf/binding/soap/interceptor/SoapPreProtocolOutInterceptor.java
        //  94     private void ensureMimeHeaders(SoapMessage message) {
        // 95         if (message.get(MIME_HEADERS) == null) {
        // 96             message.put(MIME_HEADERS, new HashMap<String, List<String>>());
        // 97         }
        // 98         String cte = (String)message.getContextualProperty(Message.CONTENT_TRANSFER_ENCODING);  // <<<=== read property CONTENT_TRANSFER_ENCODING in message property
        // 99         if (cte != null) {
        //100             //root part MUST be binary
        //101             message.put(Message.CONTENT_TRANSFER_ENCODING, "binary");
        //102             message.put("soap.attachement.content.transfer.encoding", cte);
        //103         }
        //104     }
        super(Phase.PRE_LOGICAL);

        // If we set phase PREPARE_SEND or after, CXF runtime automatically set Content-Transfer-Encoding: base64 to root part.
        // It brings unintended consequences. Content body that is SOAP message itself is not encoded base64 as the following example of message.
        // SOAP client try to parse SOAP message base on Content-Transfer-Encoding header, so the client regards overall SOAP message is encoded base64,
        // but the SOAP message itself keeps remaining xml, then occur exception on soap client side "java.io.IOException: Base64 encoding error, data truncated: 207 ...".
        // Hence I think appropriate Phase for this interceptor is PRE_LOGICAL.
        //
        // Content-Type: text/xml; charset=UTF-8
        // Content-Transfer-Encoding: base64
        // Content-ID: <root.message@cxf.apache.org>
        //
        //<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
//        super(Phase.PREPARE_SEND);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        try {
            // TODO Get file name from SOAP message body
            ByteArrayInputStream in = readFileToByteArray("/tmp/helloworld.pdf");
            Map<String, List<String>> headers = attachmentHeaders("helloworld.pdf");
            Attachment attachment = AttachmentFactory.createAttachment(in, headers);
            message.setAttachments(Arrays.asList(attachment));
            message.put(Message.CONTENT_TRANSFER_ENCODING, "base64");
        } catch (IOException e) {
            throw new Fault(e);
        }
    }

    private Map<String, List<String>> attachmentHeaders(String fileName) {
        Map<String, List<String>> headers = new HashMap<>();
        // TODO Automatic detection Content-Type from the attached file extension
        headers.put("Content-Type", Arrays.asList("application/pdf"));
        headers.put("Content-ID", Arrays.asList("http://localhost:8080/swa-server/" + fileName));
        return headers;
    }

    private ByteArrayInputStream readFileToByteArray(String filePath) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            int readBytes;
            while (EOF != (readBytes = bis.read(buf))) {
                output.write(buf, 0, readBytes);
            }
        }
        return new ByteArrayInputStream(output.toByteArray());
    }
}
