package com.uem.uem_server.dto;

import lombok.Data;

@Data
public class DeviceMetricsDTO {

    private String deviceMacId;

    private double cpuUsage;

    private long totalMemory;
    private long availableMemory;

    private long totalDisk;
    private long freeDisk;

    private double batteryLevel;

    private long bytesSent;
    private long bytesReceived;
}