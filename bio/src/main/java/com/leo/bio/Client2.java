package com.leo.bio;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Deseription : 测试 BIO的阻塞 Client
 * @Author : xiangguang.wu
 * @Date : 2020/11/3
 **/
public class Client2 {
    private static final Logger log = LogManager.getLogger(Client2.class);

    public static void main(String[] args) throws IOException {
        //服务器地址
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9998);

        Socket socket = new Socket();
        socket.connect(socketAddress);
        log.info("client connect!");

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                //键盘输入
                String clientMsg = scanner.nextLine();
                if (StringUtils.isBlank(clientMsg)) {
                    System.out.println("输入内容为null，不发送");
                    continue;
                }
                if ("exit".equalsIgnoreCase(clientMsg)) {
                    System.exit(0);
                }

                //向Server发送消息
                outputStream.writeUTF(clientMsg);
                outputStream.flush();

                //接收server消息
                String serverMsg = inputStream.readUTF();
                log.info("ServerMsg = {}", serverMsg);
            }

        } catch (Exception e) {
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
