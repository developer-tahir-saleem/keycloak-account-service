package com.keycloak.accountservice.service;

import com.keycloak.accountservice.model.RoleGroup;

import java.util.UUID;

public interface RoleGroupService {

    public RoleGroup insert(RoleGroup item);

    public Iterable<RoleGroup> findAll();

    public RoleGroup findByItem(RoleGroup item);

    public RoleGroup findById(UUID it);

    public RoleGroup delete(RoleGroup item);

    public RoleGroup update(RoleGroup item);

}
