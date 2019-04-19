package com.keycloak.accountservice.service;

import com.keycloak.accountservice.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {

    public Role findByKey(String Key);

    public Role insert(Role item);

    public Iterable<Role> findAll();

    public Role findByItem(Role item);

    public Role findById(UUID it);

    public Role delete(UUID item);

    public Role update(Role item);

    public Page<Role> findListPage(Pageable pageable);


}
