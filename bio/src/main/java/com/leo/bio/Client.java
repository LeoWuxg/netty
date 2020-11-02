package com.leo.bio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @ClassName: Client
 * @Description: 客户端
 * @Create by: LeoWu
 * @Date: 2020/11/2
 */
public class Client {
    public static final Logger log = LogManager.getLogger(Client.class);

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            socket = new Socket();
            socket.connect(address);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            //向Server传输数据
            outputStream.writeUTF("Leo");
            outputStream.flush();

            //接收Server的回应
            log.info(inputStream.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}
