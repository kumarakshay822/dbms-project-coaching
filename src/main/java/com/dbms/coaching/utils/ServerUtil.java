package com.dbms.coaching.utils;

import java.net.InetAddress;

import org.springframework.stereotype.Component;

@Component
public class ServerUtil {
    public String getHostAddressAndPort() {
        String address = "";
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return address + ":8080";
    }
}
