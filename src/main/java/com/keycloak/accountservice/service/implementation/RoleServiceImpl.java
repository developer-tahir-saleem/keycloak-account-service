package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.exception.ResourceNotFoundException;
import com.keycloak.accountservice.model.Role;
import com.keycloak.accountservice.repository.RoleRepository;
import com.keycloak.accountservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository itemRepo;

    @Override
    public Iterable<Role> findAll() {
        return itemRepo.findAll();
    }

    @Override
    public Role findById(UUID id) {
        Optional<Role> itemOpt = Optional.ofNullable(itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id)));
        return itemOpt.get();
    }

    @Override
    public Role findByItem(Role item) {
        return this.findById(item.getId());
    }


    @Override
    public Role findByKey(String Key) {
        Optional<Role> itemOpt = Optional.ofNullable(itemRepo.findByName(Key)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Name ", Key)));
        return itemOpt.get();
    }


    @Override
    public Role insert(Role item) {
        Optional<Role> itemOpt = itemRepo.findByName(item.getName());
        if (itemOpt.isPresent()) {
            return itemOpt.get();
        } else {
            return itemRepo.save(item);
        }
    }

    @Override
    public Role delete(UUID item) {
        Role role = this.findById(item);
        role.setDeleted(true);
        return itemRepo.save(role);
    }

    @Override
    public Role update(Role item) {
        this.findByItem(item);
        return itemRepo.save(item);
    }

    @Override
    public Page<Role> findListPage(Pageable pageable) {
        return itemRepo.findAllByDeleted(pageable, false);
    }

}
