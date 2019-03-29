package com.keycloak.accountservice.response;


import com.keycloak.accountservice.model.Role;
import com.keycloak.accountservice.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.List;


@Getter
@Setter
@ToString
public class ResponseRegister {


    private String message = "User Registration Successfully ! ";
    private User user;
    private boolean Status;
}
