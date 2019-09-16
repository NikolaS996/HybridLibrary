package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.services.serviceImplementation.BookServiceImpl;
import com.hybrid.internship.library.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(
                userService.findAll().stream()
                .map(user -> conversionService.convert(user, UserDto.class))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(conversionService.convert(userService.findById(id), UserDto.class));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(conversionService.convert(userService.findByUsername(username), UserDto.class));
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        //user.setPassword(hashPassword(user.getPassword()));
        return ResponseEntity.ok(conversionService.convert(userService.create(user), UserDto.class));
    }

    @PutMapping("")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user){
        return ResponseEntity.ok(conversionService.convert(userService.update(user), UserDto.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
