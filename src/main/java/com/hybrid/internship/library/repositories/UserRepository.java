package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User WHERE username = ?1")
    User findByUsername(String username);
}
