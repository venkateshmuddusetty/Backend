package com.ibmw.userportal.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ibmw.userportal.dto.RoleDto;
import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.model.User;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper 
{

    public static UserDto toUserDto(User user) 
    {
        return new UserDto()
                .setEmail(user.getEmail())
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())));
    }

}