package com.keycloak.accountservice.repository;



import com.keycloak.accountservice.model.RoleGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by tahir on 2/11/19.
 */
public interface RoleGroupRepository extends PagingAndSortingRepository<RoleGroup, UUID> {
}
