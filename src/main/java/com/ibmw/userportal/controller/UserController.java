package com.ibmw.userportal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.response.Response;
import com.ibmw.userportal.service.UserService;

@CrossOrigin(origins = "*", maxAge = 4200)
@RestController
@RequestMapping("/home")
public class UserController {
	@Autowired
	private UserService userService;

	

//	private UserDto registerUser(UserSignupRequest userSignupRequest, boolean isAdmin) {
//
//		return userService.signup(userDto);
//	}

	@GetMapping("/user/finduser")
	public Response findUserByEmail(@RequestParam(required = true) String email) {
		return Response.ok().setPayload(userService.findUserByEmail(email));
	}

	@PutMapping("/user/updateuser")
	public Response UpdateUserByEmail(@RequestBody @Valid UserDto user) {
		return Response.ok().setPayload(userService.updateProfile(user));
	}

	@PutMapping("/user/changepassword")
	public Response changepassword(@RequestBody @Valid UserDto user,
			@RequestParam(required = true) String newPassword) {
		return Response.ok().setPayload(userService.changePassword(user, newPassword));
	}
}
