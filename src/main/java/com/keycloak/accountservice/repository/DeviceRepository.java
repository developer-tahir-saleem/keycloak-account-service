package com.keycloak.accountservice.repository;

import com.keycloak.accountservice.model.Device;
import com.keycloak.accountservice.model.User;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by tahir on 2/11/19.
 */
public interface DeviceRepository extends Repository<Device, Long> {

    Collection<Device> findAll();

    Optional<Device> findById(Long id);

    Optional<Device> findByUserId(String id);
    Optional<Device> findByHardwareId(String id);
    Optional<Device> findByUserIdAndDeviceToken(String id,String token);

    Device save(Device account);

    void deleteById(Long id);
}

