package com.leo.bio;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Deseription : 测试 BIO的阻塞 Server
 * @Author : xiangguang.wu
 * @Date : 2020/11/3
 **/
public class Server2 {
    private static final Logger log = LogManager.getLogger(Server2.class);

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public static void main(String[] args) {
        Server2 server2 = new Server2();
        server2.sendMsgBack();
    }

    private void sendMsgBack() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9998));

            log.info("server2 started!");
            socket = serverSocket.accept();
            //从socket获取输入输出流
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            while(true) {
                String clientMsg = inputStream.readUTF();
                if (StringUtils.isNoneBlank(clientMsg)) {
                    log.info("ClientMsg = {}", clientMsg);
                }

                //从键盘输入获取发送到client的msg
                String serverMsg = scanner.nextLine();
                if (StringUtils.isBlank(serverMsg)) {
                    System.out.println("输入内容为null，不发送");
                    continue;
                }
                if ("exit".equalsIgnoreCase(serverMsg)) {
                    System.exit(0);
                }
                //向Client发送消息
                outputStream.writeUTF(serverMsg);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //必须关闭输入输出流以及socket
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
