package com.keycloak.accountservice.repository;


import com.keycloak.accountservice.model.AppConfig;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by tahir on 2/11/19.
 */
public interface AppConfigRepository extends PagingAndSortingRepository<AppConfig, UUID> {

    Optional<AppConfig> findByAppKey(String key);


}
