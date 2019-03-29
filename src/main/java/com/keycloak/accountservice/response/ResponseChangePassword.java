package com.keycloak.accountservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseChangePassword implements Serializable {

    private String message = "Password Changed Successfully !";
    private boolean status;
}
