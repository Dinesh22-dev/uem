package com.uem.uem_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.uem_server.entity.DevicePolicy;

public interface DevicePolicyRepository
        extends JpaRepository<DevicePolicy, Long> {

    List<DevicePolicy> findByDeviceMacIdAndBlockedTrue(String macId);
}