package com.uem.uem_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.uem.uem_server.dto.DeviceMetricsDTO;
import com.uem.uem_server.dto.DeviceRegisterRequest;
import com.uem.uem_server.dto.HeartbeatRequest;
import com.uem.uem_server.entity.Device;
import com.uem.uem_server.entity.DeviceMetrics;
import com.uem.uem_server.repository.DeviceMetricsRepository;
import com.uem.uem_server.repository.DeviceRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceRepository deviceRepository;
    private final DeviceMetricsRepository deviceMetricsRepository;

    @PostMapping("/register")
    public String registerDevice(
            @RequestBody DeviceRegisterRequest request) {

        Device device = deviceRepository
                .findByDeviceMacId(request.getDeviceMacId())
                .orElse(new Device());

        device.setDeviceMacId(request.getDeviceMacId());
        device.setDeviceName(request.getDeviceName());
        device.setOsName(request.getOsName());
        device.setIpAddress(request.getIpAddress());
        device.setStatus("ONLINE");
        device.setLastSeen(LocalDateTime.now());

        deviceRepository.save(device);

        return "Device Registered";
    }

    @PostMapping("/heartbeat")
    public String heartbeat(
            @RequestBody HeartbeatRequest request) {

        System.out.println("Received heartbeat from device: " + request.getDeviceMacId());

        Device device = deviceRepository
                .findByDeviceMacId(request.getDeviceMacId())
                .orElseThrow();

        device.setLastSeen(LocalDateTime.now());
        device.setStatus("ONLINE");

        deviceRepository.save(device);

        return "Heartbeat Received";
    }

    @PostMapping("/metrics")
    public String save(@RequestBody DeviceMetricsDTO dto) {

        Device device = deviceRepository
                .findByDeviceMacId(dto.getDeviceMacId()).orElseThrow();

        DeviceMetrics metrics = new DeviceMetrics();

        metrics.setDevice(device);

        metrics.setCpuUsage(dto.getCpuUsage());

        metrics.setTotalMemory(dto.getTotalMemory());
        metrics.setAvailableMemory(dto.getAvailableMemory());

        metrics.setTotalDisk(dto.getTotalDisk());
        metrics.setFreeDisk(dto.getFreeDisk());

        metrics.setBatteryLevel(dto.getBatteryLevel());

        metrics.setBytesSent(dto.getBytesSent());
        metrics.setBytesReceived(dto.getBytesReceived());

        metrics.setTimestamp(LocalDateTime.now());

        deviceMetricsRepository.save(metrics);

        return "saved";
    }

    @GetMapping
    public Object getAllDevices() {
        return deviceRepository.findAll();
    }
}