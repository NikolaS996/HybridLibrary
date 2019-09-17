package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;

import java.util.List;


public interface UserService extends AbstractService<UserDto, Long> {

    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByUsername(String username);

    UserDto create(UserDto userDto); //Dto zbog password

    UserDto update(UserDto userDto); //Dto zbog password

    void delete(Long id);

    boolean exists(Long id);
}
