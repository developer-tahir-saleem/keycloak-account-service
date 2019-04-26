package com.keycloak.accountservice.repository;



import com.keycloak.accountservice.model.Role;
import com.keycloak.accountservice.model.RoleGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by tahir on 2/11/19.
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, UUID> {

    Optional<Role> findByName(String key);

    Page<Role> findAllByDeleted(Pageable pageable, Boolean isDeleted);

}
