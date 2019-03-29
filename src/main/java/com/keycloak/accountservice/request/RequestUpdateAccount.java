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
public class RequestUpdateAccount implements Serializable {


    @Size(min = 3, max = 64, message = "Minimum full name length: 3 - 64 characters")
    private String fullName;

    private String dateOfBirth;

    private int gender;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

}
