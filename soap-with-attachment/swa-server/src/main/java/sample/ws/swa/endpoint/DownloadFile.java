package sample.ws.swa.endpoint;

import org.apache.cxf.interceptor.OutInterceptors;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@OutInterceptors(interceptors = {"sample.ws.swa.interceptor.AddAttachmentInterceptor"})
public class DownloadFile {
    @WebMethod
    public AttachmentFile download() {
        AttachmentFile file = new AttachmentFile();
        file.setFileName("helloworld.txt");
        return file;
    }
}
