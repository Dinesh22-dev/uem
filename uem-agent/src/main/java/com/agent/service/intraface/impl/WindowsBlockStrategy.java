package com.agent.service.intraface.impl;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import com.agent.service.intraface.WebsiteBlockStrategy;

public class WindowsBlockStrategy implements WebsiteBlockStrategy {

    private static final String HOSTS_PATH = "C:\\Windows\\System32\\drivers\\etc\\hosts";

    private static final String MARKER = "# UEM_BLOCK";

    @Override
    public void apply(List<String> blockedSites) {

        try {

            File file = new File(HOSTS_PATH);

            List<String> lines = Files.readAllLines(file.toPath());

            // remove old UEM entries
            List<String> cleaned = lines.stream()
                    .filter(line -> !line.contains(MARKER))
                    .collect(Collectors.toList());

            // add new entries
            for (String site : blockedSites) {

                cleaned.add("127.0.0.1 " + site + " " + MARKER);
                cleaned.add("127.0.0.1 www." + site + " " + MARKER);
            }

            Files.write(file.toPath(), cleaned);

            // flush DNS
            Runtime.getRuntime().exec("ipconfig /flushdns");

            System.out.println("Policy applied: " + blockedSites);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}