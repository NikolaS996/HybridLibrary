package com.hybrid.internship.library.converter;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter implements AbstractConverter<User, UserDto> {

    public UserDto convertToDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName((user.getFirstName()))
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
        
        return userDto;
    }

    public User convertToEntity(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
        return user;
    }
}