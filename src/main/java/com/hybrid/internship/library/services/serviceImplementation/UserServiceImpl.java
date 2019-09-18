package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.repositories.UserRepository;
import com.hybrid.internship.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConversionService conversionService;

    @Override
    public List<User> findAll() {
//        return userRepository.findAll().
//                stream()
//                .map(user -> conversionService.convert(user, UserDto.class))
//                .collect(Collectors.toList());
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        //return conversionService.convert(userRepository.findById(id).orElseGet(() -> null), UserDto.class);
        return userRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public User findByUsername(String username) {
        //return conversionService.convert(userRepository.findByUsername(username), UserDto.class);}
        return userRepository.findByUsername(username);
    }

    @Override
    public User create(User user) {
        //return conversionService.convert(userRepository.save(conversionService.convert(userDto, User.class)), UserDto.class);
            return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        //return conversionService.convert(userRepository.save(conversionService.convert(userDto, User.class)), UserDto.class);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }
}
