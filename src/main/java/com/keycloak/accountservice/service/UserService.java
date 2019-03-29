package com.keycloak.accountservice.service;

import com.keycloak.accountservice.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    User findOne(long id);
    User findByAuthId(String id);
    User findByEmail(String email);
    void delete(long id);

}



