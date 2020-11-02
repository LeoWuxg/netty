package com.leo.bio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: Server
 * @Description: BIO服务端
 * @Create by: LeoWu
 * @Date: 2020/11/2
 */
public class Server {
    private static final Logger log = LogManager.getLogger(Server.class);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        //绑定一个地址和端口
        serverSocket.bind(new InetSocketAddress(9999));
        log.info("server started!");

        while (true) {
            new Thread(new ServerTask(serverSocket.accept())).start();
        }
    }

    public static class ServerTask implements Runnable {
        private Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ) {
                //server收到client的消息
                String userName = inputStream.readUTF();
                log.info("accept client msg:{}", userName);

                //对消息加工后，返回给client
                String backMsg = String.format("hello {} from server!", userName);
                outputStream.writeUTF(backMsg);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
