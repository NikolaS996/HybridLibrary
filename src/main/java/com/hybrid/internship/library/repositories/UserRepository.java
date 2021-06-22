package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value="SELECT * FROM User WHERE username = ?1 and id is not ?2")
    Optional<User> findByUsernameForUpdate(String username, Long id);
}
