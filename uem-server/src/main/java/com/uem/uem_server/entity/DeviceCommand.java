package com.uem.uem_server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DeviceCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceMacId;

    private String command; // RESTART / SHUTDOWN / SLEEP

    private String status; // PENDING / EXECUTED / FAILED

    private LocalDateTime createdAt;
}