package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.repositories.UserRepository;
import com.hybrid.internship.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConversionService conversionService;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().
                stream()
                .map(user -> conversionService.convert(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return conversionService.convert(userRepository.findById(id).orElseGet(() -> null), UserDto.class);
    }

    @Override
    public UserDto findByUsername(String username){
        return conversionService.convert(userRepository.findByUsername(username), UserDto.class);}

    @Override
    public UserDto create(UserDto userDto) {
        return conversionService.convert(userRepository.save(conversionService.convert(userDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return conversionService.convert(userRepository.save(conversionService.convert(userDto, User.class)), UserDto.class);
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
