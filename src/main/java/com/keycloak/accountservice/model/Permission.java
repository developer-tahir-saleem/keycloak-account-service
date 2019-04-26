package com.keycloak.accountservice.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

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

    private String description;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
    @JsonIdentityReference(alwaysAsId = true)
    private Permission parent;
    @OneToMany(mappedBy = "parent")
    private Collection<Permission> children;


}
