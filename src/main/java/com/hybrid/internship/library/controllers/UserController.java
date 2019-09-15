package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(
                userService.findAll().stream()
                .map(user -> conversionService.convert(user, UserDto.class))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(conversionService.convert(userService.findById(id), UserDto.class));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(conversionService.convert(userService.findByUsername(username), UserDto.class));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        return ResponseEntity.ok(conversionService.convert(userService.create(user), UserDto.class));
    }

    @PutMapping("/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user){
        return ResponseEntity.ok(conversionService.convert(userService.update(user), UserDto.class));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
