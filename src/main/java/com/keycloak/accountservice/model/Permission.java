package com.keycloak.accountservice.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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
public class Permission extends BaseModel {

    @NotNull
    @Column(nullable = false)
    @NotNull
    @Size( message = "Name is Required!")
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    protected Permission parent;

    @OneToMany(mappedBy = "parent")
    protected Set<Permission> children;


}
