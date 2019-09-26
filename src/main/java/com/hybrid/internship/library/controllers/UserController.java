package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.UserConverter;
import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.exceptions.RentedCopiesException;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            log.info("Currently there are no users to fetch.");
            return ResponseEntity.notFound().build();
        }
        log.info("Users are fetched.");
        return ResponseEntity.ok(
                users.stream()
                        .map(user -> userConverter.convertToDto(user))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> {
                    log.info("User {} is fetched.", user);
                    return ResponseEntity.ok(userConverter.convertToDto(user));
                })
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username)
                .map(user -> {
                    log.info("User {} is fetched.", user);
                    return ResponseEntity.ok(userConverter.convertToDto(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        //user.setPassword(hashPassword(user.getPassword()));
        User convertedUser = userConverter.convertToEntity(userDto);
        convertedUser = userService.create(convertedUser);
        if (convertedUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userConverter.convertToDto(convertedUser));
    }

    @PostMapping("/create-password/{id}")
    public ResponseEntity createPassword(@PathVariable("id") Long id, @RequestBody String password) {
        User user = userService.findById(id).orElseGet(() -> null);
//        if (user == null || user.getPassword() != null) {
//            log.info("");
//            return ResponseEntity.notFound().build();
//        }
        if(user == null)
        {
            log.info("User with ID {} wasn't found.", id);
            if(user.getPassword() != null){
                log.info("User with ID {} already has password set.", id);
            }
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        user = userService.update(user);
        inMemoryUserDetailsManager.createUser(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER")
                        .build());
        log.info("Password successfully created.");
        return ResponseEntity.ok(userConverter.convertToDto(user));
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User convertedUser = userConverter.convertToEntity(userDto);
        convertedUser = userService.update(convertedUser);
        if (convertedUser == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userConverter.convertToDto(convertedUser));
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
        } catch (RentedCopiesException rce) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
