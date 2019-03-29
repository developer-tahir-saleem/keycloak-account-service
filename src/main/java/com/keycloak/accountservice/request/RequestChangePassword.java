package com.keycloak.accountservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestChangePassword implements Serializable {

    @Email
    private String email;
    @NotNull
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;
}
