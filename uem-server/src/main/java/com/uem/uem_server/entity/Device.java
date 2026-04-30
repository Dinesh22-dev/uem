package com.uem.uem_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String deviceId;

    @Column(unique = true)
    private String deviceMacId;

    private String deviceName;

    private String osName;

    private String ipAddress;

    private String status;

    private LocalDateTime lastSeen;
}