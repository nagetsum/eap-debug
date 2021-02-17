package org.example.soap.server;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

@WebService
@MTOM
public class FileReceiverService implements FileReceiver {
    private static AtomicInteger cnt = new AtomicInteger();

    @Override
    public String uploadFile(@XmlMimeType("application/octet-stream") DataHandler handler, String fileName) {
        try {
            Files.copy(handler.getInputStream(), Paths.get("/home/nagetsum/store/uploaded" + cnt.incrementAndGet()));
        } catch (IOException e) {
            e.printStackTrace();
            return "FAILED";
        }
        return "SUCCEED";
    }
}
