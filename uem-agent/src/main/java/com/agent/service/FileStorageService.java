package com.agent.service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class FileStorageService {

    public static String getOrCreateDeviceId() {

        try {

            File file = new File("device.id");

            if (file.exists()) {
                return new String(Files.readAllBytes(file.toPath()));
            }

            String deviceId =
                    getMac();

            FileWriter writer = new FileWriter(file);
            writer.write(deviceId);
            writer.close();

            return deviceId;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMac() {
        try {

            InetAddress ip = InetAddress.getLocalHost();

            NetworkInterface network =
                    NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format(
                        "%02X%s",
                        mac[i],
                        (i < mac.length - 1) ? "-" : ""
                ));
            }

            return sb.toString();

        } catch (Exception e) {
            return "UNKNOWN";
        }
    }
}