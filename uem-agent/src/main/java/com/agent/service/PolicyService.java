package com.agent.service;

import com.agent.config.Config;

import java.util.List;

public class PolicyService {

    public static void start(String macId) {

        new Thread(() -> {

            while (true) {

                try {

                    String url = Config.SERVER_URL + "/policy/" + macId;

                    List<String> sites = HttpClientService.getListString(url);

                    WebsiteBlockService.applyPolicy(sites);

                    Thread.sleep(60000); // every 1 min

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}