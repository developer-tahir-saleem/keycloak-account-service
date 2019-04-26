package com.keycloak.accountservice.controller;

import com.keycloak.accountservice.model.Permission;
import com.keycloak.accountservice.model.Permission;
import com.keycloak.accountservice.service.KeycloakService;
import com.keycloak.accountservice.service.PermissionService;
import com.keycloak.accountservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService service;


//    @GetMapping
//    Page<Permission> getPageable(Pageable pageable) {
//        return service.findListPage(pageable);
//    }

    @GetMapping()
    public Iterable<Permission> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Permission insert(@Valid @RequestBody Permission item) {
        Permission getParent = service.insert(item);

        for (Permission child : item.getChildren()) {
            child.setParent(getParent);
            service.insert(child);
        }
        return getParent;
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Permission findById(@PathVariable("uuid") UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Permission delete(@PathVariable UUID id) {
        Permission Permission = service.delete(id);
        return Permission;
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Permission update(@RequestBody Permission item) {
        return service.update(item);
    }

}
