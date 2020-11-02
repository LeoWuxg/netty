package com.leo.netBasic;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ClassName: UseNetWorkInterface
 * @Description: TODO
 * @Create by: LeoWu
 * @Date: 2020/11/2
 */
public class UseNetworkInterface {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
    }
}
