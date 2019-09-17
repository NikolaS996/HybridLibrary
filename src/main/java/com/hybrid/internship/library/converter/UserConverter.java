package com.hybrid.internship.library.converter;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import com.google.common.collect.ImmutableSet;


import java.util.Set;

public class UserConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(User.class, UserDto.class),
                new ConvertiblePair(UserDto.class, User.class)};
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object o, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (o instanceof User)
            return convertUser(o); //cast ovde ili unutar metode convertBook?
        else if (o instanceof UserDto)
            return convertUserDto(o);
        else
            return null; //Exception
    }

    private UserDto convertUser(Object o) {
        User user = (User) o;
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName((user.getFirstName()))
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
        
        return userDto;
    }

    private User convertUserDto(Object o) {
        UserDto userDto = (UserDto) o;
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