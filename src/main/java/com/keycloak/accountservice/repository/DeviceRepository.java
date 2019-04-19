package com.keycloak.accountservice.repository;

import com.keycloak.accountservice.model.Device;
import com.keycloak.accountservice.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
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

    @Transactional
    @Modifying
    @Query("UPDATE Device d SET d.isLoggedin = false WHERE d.userId = :userId")
    int logoutAnyWhere(@Param("userId") String userId);


    void deleteById(Long id);
}

