package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.exception.ResourceNotFoundException;
import com.keycloak.accountservice.model.Permission;
import com.keycloak.accountservice.repository.PermissionRepository;
import com.keycloak.accountservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by khawar on 1/31/19.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository itemRepo;
    @Override
    public Iterable<Permission> findAll() {
        return  itemRepo.findAllByParent(null);
    }

    @Override
    public Permission findById(UUID id) {
        Optional<Permission> itemOpt = Optional.ofNullable(itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", "id", id)));
        return itemOpt.get();
    }

    @Override
    public Permission findByItem(Permission item){
        return this.findById(item.getId());
    }

    @Override
    public Permission insert(Permission item) {
        return itemRepo.save(item);
    }

    @Override
    public Permission delete(UUID item) {
      Permission permission =  this.findById(item);
        return itemRepo.save(permission);
    }

    @Override
    public Permission update(Permission item) {
        this.findByItem(item);
        return itemRepo.save(item);
    }

    @Override
    public Page<Permission> findListPage(Pageable pageable) {
        return itemRepo.findAllByDeleted(pageable, false);
    }

}
