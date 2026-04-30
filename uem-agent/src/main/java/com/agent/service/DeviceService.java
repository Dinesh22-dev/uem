package com.agent.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class DeviceService {

    public static String getHostName() throws Exception {
        return InetAddress.getLocalHost().getHostName();
    }

    public static String getIpAddress() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String getOs() {
        return System.getProperty("os.name");
    }

    public static String getMacAddress() {

        try {

            Enumeration<NetworkInterface> networks =
                    NetworkInterface.getNetworkInterfaces();

            while (networks.hasMoreElements()) {

                NetworkInterface network =
                        networks.nextElement();

                byte[] mac = network.getHardwareAddress();

                if (mac != null) {

                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < mac.length; i++) {

                        sb.append(String.format(
                                "%02X%s",
                                mac[i],
                                (i < mac.length - 1) ? "-" : ""
                        ));
                    }

                    return sb.toString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "UNKNOWN";
    }
}