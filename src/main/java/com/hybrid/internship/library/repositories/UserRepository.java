package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
