package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user")
    ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(
                userService.findAll().stream()
                .map(user -> conversionService.convert(user, UserDto.class))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserDto> getOneById(@PathVariable Long id){
        return ResponseEntity.ok(conversionService.convert(userService.findById(id), UserDto.class));
    }
}
