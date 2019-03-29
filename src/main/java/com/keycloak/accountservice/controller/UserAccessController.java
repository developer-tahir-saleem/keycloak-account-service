package com.keycloak.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserAccessController {

    @GetMapping("/test")
    public String publica() {
        String msg = String.format("Welcome To Public Auth API");
        return msg;
    }

}
