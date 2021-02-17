package org.example.soap.server;

import javax.activation.DataHandler;

public interface FileReceiver {
    public String uploadFile(DataHandler handler, String fileName);
}
