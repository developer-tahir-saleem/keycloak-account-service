package com.keycloak.accountservice.service;


import com.keycloak.accountservice.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Created by tahir on 2/11/19.
 */
public interface PermissionService {

    public Permission insert(Permission item);
    public Iterable<Permission> findAll();
    public Permission findByItem(Permission item);
    public Permission findById(UUID it);
    public Permission delete(UUID item);
    public Permission update(Permission item);
    public Page<Permission> findListPage(Pageable pageable);

}
