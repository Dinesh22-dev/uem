package com.agent.service;

import oshi.SystemInfo;
import oshi.hardware.*;

import java.util.List;

public class SystemInfoService {

    private static final SystemInfo si = new SystemInfo();

    public static double getCpuUsage() {
        CentralProcessor cpu = si.getHardware().getProcessor();
        return cpu.getSystemCpuLoad(1000) * 100;
    }

    public static long getTotalMemory() {
        return si.getHardware().getMemory().getTotal() / (1024 * 1024);
    }

    public static long getAvailableMemory() {
        return si.getHardware().getMemory().getAvailable() / (1024 * 1024);
    }

    public static long getTotalDisk() {

        long total = 0;

        for (HWDiskStore disk : si.getHardware().getDiskStores()) {
            total += disk.getSize();
        }

        return total / (1024 * 1024);
    }

    public static long getFreeDisk() {

        long free = 0;

        for (HWDiskStore disk : si.getHardware().getDiskStores()) {
            free += disk.getSize() - disk.getWriteBytes();
        }

        return free / (1024 * 1024);
    }

    public static double getBatteryLevel() {

        List<PowerSource> ps =
                si.getHardware().getPowerSources();

        if (ps.isEmpty()) return -1;

        return ps.get(0).getRemainingCapacityPercent() * 100;
    }

    public static long getBytesSent() {

        long sent = 0;

        for (NetworkIF net :
                si.getHardware().getNetworkIFs()) {

            net.updateAttributes();
            sent += net.getBytesSent();
        }

        return sent;
    }

    public static long getBytesReceived() {

        long received = 0;

        for (NetworkIF net :
                si.getHardware().getNetworkIFs()) {

            net.updateAttributes();
            received += net.getBytesRecv();
        }

        return received;
    }
}