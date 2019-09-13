package com.hybrid.internship.library.converter;

import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.User;
import lombok.EqualsAndHashCode;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import com.google.common.collect.ImmutableSet;


import java.util.Set;

public class UserConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(User.class, UserDto.class)};
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
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getEmail());
        userDto.setEmail(user.getEmail());
        
        return userDto;
    }

    private User convertUserDto(Object o) {
        UserDto userDto = (UserDto) o;
        User user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        return user;
    }
}