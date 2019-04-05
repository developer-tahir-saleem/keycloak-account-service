package com.keycloak.accountservice.service;


import com.keycloak.accountservice.model.AppConfig;

import java.util.UUID;

/**
 * Created by tahir on 2/11/19.
 */
public interface AppConfigService {

    public AppConfig insert(AppConfig item);
    public Iterable<AppConfig> findAll();
    public AppConfig findByItem(AppConfig item);
    public AppConfig findByKey(String Key);
    public AppConfig findById(UUID id);
    public AppConfig delete(AppConfig item);
    public AppConfig update(AppConfig item);

}
