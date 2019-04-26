package com.keycloak.accountservice.controller;

import com.keycloak.accountservice.config.EncodeDecode;
import com.keycloak.accountservice.model.AppConfig;
import com.keycloak.accountservice.model.Role;
import com.keycloak.accountservice.service.AppConfigService;
import com.keycloak.accountservice.service.KeycloakService;
import com.keycloak.accountservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @Autowired
    private KeycloakService keycloakService;

    @GetMapping
    Page<Role> getPageable(Pageable pageable) {
        return service.findListPage(pageable);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Role> getAll(Pageable pageable) {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Role insert(@Valid @RequestBody Role item) {
        keycloakService.addRole(item.getName());
        return service.insert(item);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Role findById(@PathVariable("uuid") UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Role delete(@PathVariable UUID id) {
       Role role = service.delete(id);
        keycloakService.removeRole(role.getName());
        return  role;
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Role update(@RequestBody Role item) {
        keycloakService.updateRole(item.getName());
        return service.update(item);
    }

}
