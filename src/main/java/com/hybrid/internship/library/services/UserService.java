package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.User;


public interface UserService extends AbstractService<User, Long> {

    User findByUsername(String username);

}
