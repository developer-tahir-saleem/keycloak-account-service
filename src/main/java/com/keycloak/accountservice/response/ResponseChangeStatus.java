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
public class ResponseChangeStatus implements Serializable {

    private String message = "Status Changed Successfully !";
    private boolean status;
}

