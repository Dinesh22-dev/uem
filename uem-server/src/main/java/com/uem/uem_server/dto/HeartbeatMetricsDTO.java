package com.uem.uem_server.dto;

import lombok.Data;

@Data
public class HeartbeatMetricsDTO {

    private String deviceMacId;

    // CPU
    private double cpuUsage;

    // RAM
    private long totalMemory;
    private long availableMemory;

    // DISK
    private long totalDisk;
    private long freeDisk;

    // BATTERY
    private double batteryLevel;

    // NETWORK
    private long bytesSent;
    private long bytesReceived;
}