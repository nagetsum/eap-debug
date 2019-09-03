package sample.ws.swa.servlet;

import sample.ws.swa.endpoint.AttachmentFile;
import sample.ws.swa.endpoint.DownloadFile;
import sample.ws.swa.endpoint.DownloadFileService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        DownloadFileService service = new DownloadFileService();
        DownloadFile port = service.getDownloadFilePort();

        ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.connectionTimeout", "5000");
        ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.receiveTimeout", "60000");

        // TODO extract MIME attachment file from SOAP response message
        AttachmentFile downloaded = port.download();
        res.setContentType("text/plain; charset=utf-8");
        res.getWriter().println("download file via SwA: " + downloaded.getFileName());
    }
}
