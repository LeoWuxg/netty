package com.leo.netBasic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: UseInet
 * @Description: InetAddress基本使用
 * @Create by: LeoWu
 * @Date: 2020/10/26
 */
public class UseInet {
    private static final Logger log = LogManager.getLogger(UseInet.class);

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        log.info("inetAddress = {}", inetAddress);

        InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress address : allByName) {
            log.info(address);
        }

        byte[] address = {(byte) 112, 80,(byte) 248, 76};
        InetAddress ia = InetAddress.getByAddress(address);
        log.info(ia);
    }
}
