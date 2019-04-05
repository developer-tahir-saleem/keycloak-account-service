package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.exception.ResourceNotFoundException;
import com.keycloak.accountservice.model.AppConfig;
import com.keycloak.accountservice.repository.AppConfigRepository;
import com.keycloak.accountservice.service.AppConfigService;
import com.keycloak.accountservice.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by khawar on 1/31/19.
 */
@Service("appConfigService")
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    AppConfigRepository itemRepo;

    @Override
    public Iterable<AppConfig> findAll() {
        return itemRepo.findAll();
    }

    @Override
    public AppConfig findById(UUID id) {
        Optional<AppConfig> itemOpt = Optional.ofNullable(itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppConfig", "id", id)));
        return itemOpt.get();
    }

    @Override
    public AppConfig findByItem(AppConfig item) {
        return this.findById(item.getId());
    }

    @Override
    public AppConfig findByKey(String Key) {
        Optional<AppConfig> itemOpt = Optional.ofNullable(itemRepo.findByAppKey(Key)
                .orElseThrow(() -> new ResourceNotFoundException("AppConfig", "key ", Key)));
        return itemOpt.get();
    }

    @Override
    public AppConfig insert(AppConfig item) {
        return itemRepo.save(item);
    }

    @Override
    public AppConfig delete(AppConfig item) {
        this.findByItem(item);
        return itemRepo.save(item);
    }

    @Override
    public AppConfig update(AppConfig item) {
        this.findByItem(item);
        return itemRepo.save(item);
    }
}
