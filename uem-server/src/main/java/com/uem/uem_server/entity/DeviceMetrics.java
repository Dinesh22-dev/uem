package com.uem.uem_server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DeviceMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Device device;

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

    private LocalDateTime timestamp;
}