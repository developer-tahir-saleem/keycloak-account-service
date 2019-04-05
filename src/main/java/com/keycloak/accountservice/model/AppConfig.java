package com.keycloak.accountservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AppConfig extends BaseModel {

    @NotNull
    @Column(unique=true)
    @NotBlank(message = "Need to be Unique and not null !")
    private String appKey;

    @NotNull
    @Lob
    @Size( message = "Value is Required!")
    private String appValue;

    private String description;




}
