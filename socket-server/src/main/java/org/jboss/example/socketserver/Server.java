package org.jboss.example.socketserver;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String... args) throws IOException {
        new Server().run("0.0.0.0", 10000);
//        new Server().run("127.0.0.1", 10000);
    }

    private void run(String bindHost, int bindPort) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(bindHost, bindPort));
        ExecutorService executor = Executors.newFixedThreadPool(10);
        System.out.println(LocalDateTime.now() + " accept start ...");
        while (true) {
            Socket socket = serverSocket.accept();
            executor.submit(() -> handleRequest(socket));
        }
    }

    private void handleRequest(Socket socket) {
        System.out.println(LocalDateTime.now() + " accept from " + socket.getInetAddress());

        while (!socket.isClosed()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = reader.readLine();
                if ("echo".equals(msg)) {
                    sendResponse(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(LocalDateTime.now() + " socket closed: " + socket.getInetAddress());
    }

    private void sendResponse(Socket socket) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("echo: " + InetAddress.getLocalHost().getHostName());
        writer.newLine();
        writer.flush();
        System.out.println(LocalDateTime.now() + " send response to " + socket.getInetAddress());
    }
}
