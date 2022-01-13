package com.ibmw.userportal.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ibmw.userportal.dto.RoleDto;
import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.model.User;
import com.ibmw.userportal.model.UserProfile;

public class UserProfileMapper 
{
	public static UserProfileDto toUserDto(UserProfile user) 
    {
		return new UserProfileDto()
				.setGender(user.getGender())
				.setMiddleName(user.getMiddleName())
				.setMobileNumber(user.getMobileNumber());
    }
}
