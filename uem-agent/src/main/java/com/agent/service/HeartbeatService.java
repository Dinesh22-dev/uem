package com.agent.service;

import com.agent.config.Config;

import java.util.HashMap;
import java.util.Map;

public class HeartbeatService {

    public static void start(String deviceId) {

        System.out.println("Device Mac Id : " + deviceId);

        new Thread(() -> {

            while (true) {

                try {

                    Map<String, Object> body = new HashMap<>();

                    body.put("deviceMacId", deviceId);

                    // ✅ ADD METRICS HERE
                    body.put("cpuUsage",
                            SystemInfoService.getCpuUsage());

                    body.put("totalMemory",
                            SystemInfoService.getTotalMemory());

                    body.put("availableMemory",
                            SystemInfoService.getAvailableMemory());

                    body.put("totalDisk",
                            SystemInfoService.getTotalDisk());

                    body.put("freeDisk",
                            SystemInfoService.getFreeDisk());

                    body.put("batteryLevel",
                            SystemInfoService.getBatteryLevel());

                    body.put("bytesSent",
                            SystemInfoService.getBytesSent());

                    body.put("bytesReceived",
                            SystemInfoService.getBytesReceived());

                    HttpClientService.post(
                            Config.SERVER_URL + "/heartbeat",
                            body);

                    CommandService.start(deviceId);

                    Thread.sleep(30000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}