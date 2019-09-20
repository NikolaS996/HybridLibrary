package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.UserConverter;
import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.exceptions.RentedCopiesException;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
            List<User> users = userService.findAll();
            if (users.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(
                    users.stream()
                            .map(user -> userConverter.convertToDto(user))
                            .collect(Collectors.toList())
            );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(userConverter.convertToDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(userConverter.convertToDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        //user.setPassword(hashPassword(user.getPassword()));
        User convertedUser = userConverter.convertToEntity(userDto);
        convertedUser = userService.create(convertedUser);
        if (convertedUser == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userConverter.convertToDto(convertedUser));
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User convertedUser = userConverter.convertToEntity(userDto);
        convertedUser = userService.create(convertedUser);
        if (convertedUser == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userConverter.convertToDto(convertedUser));
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        try{
            userService.delete(id);
        }
        catch(RentedCopiesException rce){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User can't be deleted because they didn't return book(s).");
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
