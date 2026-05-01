package com.agent.service;

public class StartupService {

    public static void addToStartup() {

        try {

            String startupPath = System.getenv("APPDATA") +
                    "\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";

            String batFilePath = startupPath + "\\uem-agent.bat";

            java.io.File file = new java.io.File(batFilePath);

            // already exists → skip
            if (file.exists()) {
                return;
            }

            String jarPath = new java.io.File(
                    StartupService.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI())
                    .getPath();

            java.io.FileWriter writer = new java.io.FileWriter(file);

            writer.write("@echo off\n");
            writer.write("java -jar \"" + jarPath + "\"\n");

            writer.close();

            System.out.println("Added to startup");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
