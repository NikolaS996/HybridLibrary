package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.User;

import java.util.Optional;


public interface UserService extends AbstractService<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean isUsernameAvailable(String username);

}
