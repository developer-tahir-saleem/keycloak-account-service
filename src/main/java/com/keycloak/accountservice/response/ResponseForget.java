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
public class ResponseForget implements Serializable {

    private String message = " Forget Password Email Send Successfully !";
    private boolean status;
}
