package com.uem.uem_server.dto;

import lombok.Data;

@Data
public class DeviceRegisterRequest {

    private String deviceMacId;

    private String deviceName;

    private String osName;

    private String ipAddress;
}