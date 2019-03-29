package com.keycloak.accountservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by tahir on 2/11/19.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    private String authId;

    @Size(min = 3, max = 64, message = "Minimum full name length: 3 - 64 characters")
    private String fullName;

    private String dateOfBirth;

    private int gender;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;


    @Size(min = 8, max = 64, message = "Minimum password length: 8 - 64 characters")
    @Transient
    private String password;

//    private String type;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Role> roles;
//    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

}
