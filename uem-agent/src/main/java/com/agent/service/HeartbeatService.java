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

                    Map<String, Object> body =
                            new HashMap<>();

                    body.put("deviceMacId", deviceId);

                    HttpClientService.post(
                            Config.SERVER_URL + "/heartbeat",
                            body
                    );

                    Thread.sleep(30000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}