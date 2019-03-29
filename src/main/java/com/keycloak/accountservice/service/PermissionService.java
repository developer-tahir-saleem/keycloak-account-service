package com.keycloak.accountservice.service;


import com.keycloak.accountservice.model.Permission;

import java.util.UUID;

/**
 * Created by tahir on 2/11/19.
 */
public interface PermissionService {

    public Permission insert(Permission item);
    public Iterable<Permission> findAll();
    public Permission findByItem(Permission item);
    public Permission findById(UUID it);
    public Permission delete(Permission item);
    public Permission update(Permission item);

}
