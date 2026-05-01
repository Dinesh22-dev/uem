package com.agent.service;

import java.util.HashMap;
import java.util.Map;

public class MetricsService {

        public static void start(String deviceId) {

                new Thread(() -> {

                        while (true) {

                                try {

                                        Map<String, Object> body = new HashMap<>();

                                        body.put("deviceMacId", deviceId);

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
                                                        "http://192.168.1.11:8081/metrics",
                                                        body);

                                        Thread.sleep(30000);

                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }

                }).start();
        }
}