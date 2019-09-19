package sample.ws.swa.interceptor;

import org.apache.cxf.attachment.AttachmentDataSource;
import org.apache.cxf.attachment.AttachmentImpl;
import org.apache.cxf.attachment.AttachmentUtil;
import org.apache.cxf.message.Attachment;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.io.*;
import java.util.List;
import java.util.Map;

public class AttachmentFactory {

    public static Attachment createAttachment(InputStream stream, Map<String, List<String>> headers)
            throws IOException {
        String id = AttachmentUtil.cleanContentId(getHeader(headers, "Content-ID"));
        AttachmentImpl att = new AttachmentImpl(id);
        DataSource source = new AttachmentDataSource(getHeader(headers, "Content-Type"), stream);
        att.setDataHandler(new DataHandler(source));
        return att;
    }

    private static String getHeaderValue(List<String> v) {
        if (v != null && v.size() > 0) {
            return v.get(0);
        }
        return null;
    }

    private static String getHeader(Map<String, List<String>> headers, String h) {
        return getHeaderValue(headers.get(h));
    }
}