package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.exception.ResourceNotFoundException;
import com.keycloak.accountservice.model.RoleGroup;
import com.keycloak.accountservice.repository.RoleGroupRepository;
import com.keycloak.accountservice.service.RoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("roleGroupService")
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    RoleGroupRepository itemRepo;

    @Override
    public Iterable<RoleGroup> findAll() {
        return  itemRepo.findAll();
    }

    @Override
    public RoleGroup findById(UUID id) {
        Optional<RoleGroup> itemOpt = Optional.ofNullable(itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoleGroup", "id", id)));
        return itemOpt.get();
    }

    @Override
    public RoleGroup findByItem(RoleGroup item){
        return this.findById(item.getId());
    }

    @Override
    public RoleGroup insert(RoleGroup item) {
        return itemRepo.save(item);
    }

    @Override
    public RoleGroup delete(RoleGroup item) {
        this.findByItem(item);
        return itemRepo.save(item);
    }

    @Override
    public RoleGroup update(RoleGroup item) {
        this.findByItem(item);
        return itemRepo.save(item);
    }

}
