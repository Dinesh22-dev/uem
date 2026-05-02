package com.agent.service.intraface.impl;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import com.agent.service.intraface.WebsiteBlockStrategy;

public class LinuxBlockStrategy implements WebsiteBlockStrategy {

    private static final String HOSTS_PATH = "/etc/hosts";
    private static final String MARKER = "# UEM_BLOCK";

    @Override
    public void apply(List<String> blockedSites) {

        try {

            File file = new File(HOSTS_PATH);

            List<String> lines = Files.readAllLines(file.toPath());

            List<String> cleaned = lines.stream()
                    .filter(line -> !line.contains(MARKER))
                    .collect(Collectors.toList());

            for (String site : blockedSites) {
                cleaned.add("127.0.0.1 " + site + " " + MARKER);
                cleaned.add("127.0.0.1 www." + site + " " + MARKER);
            }

            Files.write(file.toPath(), cleaned);

            // flush DNS (depends on distro)
            Runtime.getRuntime().exec("systemd-resolve --flush-caches");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}