package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.repositories.UserRepository;
import com.hybrid.internship.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
            return null;
        return users;
    }

    @Override
    public User getOne(Long id) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
