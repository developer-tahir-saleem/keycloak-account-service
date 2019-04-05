package com.keycloak.accountservice.service;

import com.keycloak.accountservice.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    Device save(Device device);
    List<Device> findAll();
    Device findOne(long id);
    Optional<Device> findByUserIdAndDeviceToken(String id, String token);
    void delete(long id);
    void insertOrUpdate(Device device);
    Optional<Device> findByHardwareId(String id);


}



