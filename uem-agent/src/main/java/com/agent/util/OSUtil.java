package com.agent.util;

public class OSUtil {

    public static String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean isWindows() {
        return getOS().contains("win");
    }

    public static boolean isLinux() {
        return getOS().contains("nix") || getOS().contains("nux");
    }

    public static boolean isMac() {
        return getOS().contains("mac");
    }
}
