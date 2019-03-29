package com.keycloak.accountservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
public class RoleGroup extends BaseModel {



    private String name;
    private String caption;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_group_permission",
            joinColumns = @JoinColumn(name = "role_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private List<Permission> roleGroupPermissions = new ArrayList<>();


}

