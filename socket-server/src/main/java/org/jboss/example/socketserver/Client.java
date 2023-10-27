package org.jboss.example.socketserver;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {

    private static String DEST = "127.0.0.1";
    private static int SEND_INTERVAL_MS = 1000;

    public static void main(String... args) throws IOException {
        if (args != null && args.length == 1) {
            DEST = args[0];
            System.out.println("destination ip : " + DEST);
        }
        new Client().run();
    }

    private void run() throws IOException {
        Socket s = new Socket();
//        s.setSoTimeout(30000);
        s.connect(new InetSocketAddress(DEST, 10000), 10000);

        for (int i = 0; i < 1000; i++) {
            sendRequest(s);
            handleResponse(s);
            sleep(SEND_INTERVAL_MS);
        }
    }

    private void sendRequest(Socket s) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        writer.write("echo");
        writer.newLine();
        writer.flush();
        System.out.println(LocalDateTime.now() + " send request: echo");
    }

    private void handleResponse(Socket s) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String msg = reader.readLine();
        System.out.println(LocalDateTime.now() + " receive: " + msg);
    }

    private void sleep(long millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
