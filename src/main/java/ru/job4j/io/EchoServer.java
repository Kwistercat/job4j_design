package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "";
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        if (str.contains("=")) {
                            if (str.contains("Hello")) {
                                answer = "Hello";
                            } else if (str.contains("Exit")) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                server.close();
                            } else {
                                answer = str.split("=")[1].split(" ")[0];
                            }
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }
}