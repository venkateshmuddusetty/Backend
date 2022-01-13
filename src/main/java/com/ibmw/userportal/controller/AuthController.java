package com.ibmw.userportal.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.payload.requests.LoginRequest;
import com.ibmw.userportal.payload.responses.JwtResponse;
import com.ibmw.userportal.response.MessageResponse;
import com.ibmw.userportal.security.jwt.JwtUtils;
import com.ibmw.userportal.service.UserService;
import com.ibmw.userportal.serviceimpl.UserDetailsImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 4200)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	JwtUtils jwtUtils;

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}
//	
	@PostMapping("/register")
	public ResponseEntity signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {

		return userService.signup(userSignupRequest);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(token, userDetails.getId(), userDetails.getUsername(),
				userDetails.getLastLoggedIn(), userDetails.getUserProfile(), roles));
	}

}
