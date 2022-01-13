package com.ibmw.userportal.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ibmw.userportal.controller.UserSignupRequest;
import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.model.User;
import com.ibmw.userportal.response.MessageResponse;

public interface UserService {

	
	  /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    ResponseEntity<MessageResponse> signup(UserSignupRequest userSignUp);

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
     UserDto updateProfile(UserDto userDto);

    /**
     * Update password
     *
     * @param newPassword
     * @return
     */
    UserDto changePassword(UserDto userDto, String newPassword);

	UserProfileDto saveOrUpdateUser(UserProfileDto userProfileDto);
}
