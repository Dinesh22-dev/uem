package com.agent;

import com.agent.config.Config;
import com.agent.service.*;

import java.util.HashMap;
import java.util.Map;

public class AgentMain {

        public static void main(String[] args) {

                try {

                        System.out.println("Agent Starting...");

                        // 1. Load or create device ID
                        String deviceId = FileStorageService.getOrCreateDeviceId();

                        System.out.println("Device ID: " + deviceId);

                        // 2. Collect device details
                        Map<String, Object> request = new HashMap<>();

                        request.put("deviceMacId", deviceId);
                        request.put("deviceName",
                                        DeviceService.getHostName());
                        request.put("osName",
                                        DeviceService.getOs());
                        request.put("ipAddress",
                                        DeviceService.getIpAddress());

                        // 3. Register device
                        HttpClientService.post(
                                        Config.SERVER_URL + "/register",
                                        request);

                        // 4. Start heartbeat
                        HeartbeatService.start(deviceId);
                        PolicyService.start(deviceId);

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}