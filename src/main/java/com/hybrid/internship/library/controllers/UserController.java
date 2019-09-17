package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.dtos.UserDto;
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

//    @Autowired
//    ConversionService conversionService;

    @Autowired
    UserServiceImpl userService;

//    @Autowired
//    BookServiceImpl bookService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
//        return ResponseEntity.ok(
//                userService.findAll().stream()
//                .map(user -> conversionService.convert(user, UserDto.class))
//                .collect(Collectors.toList())
//        );
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        //return ResponseEntity.ok(conversionService.convert(userService.findById(id), UserDto.class));
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username){
        //return ResponseEntity.ok(conversionService.convert(userService.findByUsername(username), UserDto.class));
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        //user.setPassword(hashPassword(user.getPassword()));
        //return ResponseEntity.ok(conversionService.convert(userService.create(userDto), UserDto.class));
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        //return ResponseEntity.ok(conversionService.convert(userService.update(userDto), UserDto.class));
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
