package com.uem.uem_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.uem_server.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, String> {

    Optional<Device> findByDeviceMacId(String deviceMacId);
}