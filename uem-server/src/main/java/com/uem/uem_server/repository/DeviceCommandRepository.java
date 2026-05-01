package com.uem.uem_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.uem_server.entity.DeviceCommand;

public interface DeviceCommandRepository
        extends JpaRepository<DeviceCommand, Long> {

    List<DeviceCommand> findByDeviceMacIdAndStatus(
            String macId,
            String status);
}