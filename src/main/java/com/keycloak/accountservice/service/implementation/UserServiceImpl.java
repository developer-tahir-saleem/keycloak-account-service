package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.exception.ResourceNotFoundException;
import com.keycloak.accountservice.model.User;
import com.keycloak.accountservice.repository.UserRepository;
import com.keycloak.accountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findOne(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByAuthId(String id) {

        Optional<User> itemOpt = Optional.of(userRepository.findByAuthId(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));
        return itemOpt.get();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> itemOpt = Optional.of(userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", email)));
        return itemOpt.get();
    }


    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
//            userRepository.delete(user.get());
        } else {
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


}
