package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.User;

import java.util.List;


public interface UserService extends AbstractService<User, Long> {

    List<User> findAll();

    User findById(Long id);

    User create(User user); //Dto zbog password

    User update(User user); //Dto zbog password

    void delete(Long id);

    boolean exists(Long id);
}
