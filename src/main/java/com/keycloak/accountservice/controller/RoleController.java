package com.keycloak.accountservice.controller;

import com.keycloak.accountservice.config.EncodeDecode;
import com.keycloak.accountservice.model.AppConfig;
import com.keycloak.accountservice.model.Role;
import com.keycloak.accountservice.service.AppConfigService;
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

    @GetMapping("/{key}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> findById(@PathVariable("key") String key) {
        return new ResponseEntity<>(service.findByKey(key), HttpStatus.OK);
    }

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
        return service.insert(item);
    }

    @GetMapping("/get/{uuid}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Role findById(@PathVariable("uuid") UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Role delete(@PathVariable UUID id) {
        return service.delete(id);
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Role update(@RequestBody Role item) {
        return service.update(item);
    }

}
