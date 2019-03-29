package com.keycloak.accountservice.controller;

import com.keycloak.accountservice.request.RequestUser;
import com.keycloak.accountservice.request.RequestUserRole;
import com.keycloak.accountservice.response.ResponseRemoveAccount;
import com.keycloak.accountservice.response.ResponseUser;
import com.keycloak.accountservice.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/account/manager/v1/")
public class ResourceController {


    private final KeycloakService KeycloakService;

    @Autowired
    public ResourceController(KeycloakService keycloakService) {

        this.KeycloakService = keycloakService;
    }
    @PostMapping(path = "/sample", produces = "application/json")
    public ResponseEntity sample(@RequestBody @Valid RequestUser user) {
//        KeycloakService.sample(requestRemoveAccount.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseRemoveAccount(" Hi!, user has been successfully deleted! ", true));

    }
    @PostMapping(path = "/setEmailVerifiedTrue", produces = "application/json")
    public ResponseEntity setEmailVerifiedTrue(@RequestBody @Valid RequestUser user) {
        KeycloakService.setEmailVerifiedTrue(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUser(" Hi!, user has been successfully deleted! ", true));
    }

    @PostMapping(path = "/setEmailVerifiedAsFalse", produces = "application/json")
    public ResponseEntity setEmailVerifiedAsFalse(@RequestBody @Valid RequestUser user) {
        KeycloakService.setEmailVerifiedAsFalse(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUser(" Hi!, user has been successfully deleted! ", true));
    }


    @PostMapping(path = "/addRoleToUser", produces = "application/json")
    public ResponseEntity addRoleToUser(@RequestBody @Valid RequestUserRole user) {
        KeycloakService.addRoleToUser(user.getId(),user.getName());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUser(" Hi!, user has been successfully deleted! ", true));
    }

    @PostMapping(path = "/removeRoleFromUser", produces = "application/json")
    public ResponseEntity removeRoleFromUser(@RequestBody @Valid RequestUserRole user) {
        KeycloakService.removeRoleFromUser(user.getId(),user.getName());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUser(" Hi!, user has been successfully deleted! ", true));
    }




    @PostMapping(path = "/deactivateUser", produces = "application/json")
    public ResponseEntity deactivateUser(@RequestBody @Valid RequestUserRole user) {
        KeycloakService.deactivateUser(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUser(" This user account has been successfully deactivated! ", true));
    }



    @PostMapping(path = "/enableUserAccount", produces = "application/json")
    public ResponseEntity enableUserAccount(@RequestBody @Valid RequestUserRole user) {
        KeycloakService.enableUserAccount(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUser(" This user account has been successfully enabled! ", true));
    }








}
