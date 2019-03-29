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
public class ResponseUser implements Serializable {

    private String message = "  Send Successfully !";
    private boolean status;
}
