package sample.ws.swa.interceptor;

import org.apache.cxf.attachment.AttachmentUtil;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Attachment;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAttachmentInterceptor extends AbstractPhaseInterceptor<Message> {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final int EOF = -1;

    public AddAttachmentInterceptor() {
        super(Phase.PREPARE_SEND);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        try {
            // TODO Get file name from SOAP message body
            ByteArrayInputStream in = readFileToByteArray("/tmp/helloworld.txt");
            Map<String, List<String>> headers = attachmentHeaders();
            Attachment attachment = AttachmentUtil.createAttachment(in, headers);
            message.setAttachments(Arrays.asList(attachment));
        } catch (IOException e) {
            throw new Fault(e);
        }
    }

    private Map<String, List<String>> attachmentHeaders() {
        Map<String, List<String>> headers = new HashMap<>();
        // TODO Get MIME type from attached file extension
        headers.put("Content-Type", Arrays.asList("plain/text"));
        headers.put("Content-Transfer-Encoding", Arrays.asList("binary"));
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
