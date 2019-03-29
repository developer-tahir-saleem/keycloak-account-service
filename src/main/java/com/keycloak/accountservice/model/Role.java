package com.keycloak.accountservice.model;

import lombok.*;

import javax.persistence.Entity;

/**
 * Created by tahir on 2/12/19.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseModel {



    private String name;


}

