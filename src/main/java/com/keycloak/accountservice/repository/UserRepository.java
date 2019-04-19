package com.keycloak.accountservice.repository;
import com.keycloak.accountservice.model.User;
import org.springframework.data.repository.Repository;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by tahir on 2/11/19.
 */
public interface UserRepository extends Repository<User, String> {
//    Optional<User> findByUsername(String username);

    Collection<User> findAll();

//    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByAuthId(String id);

    Optional<User> findByEmail(String email);

//    Integer countByUsername(String username);

    User save(User account);

    void deleteById(Long id);
}

