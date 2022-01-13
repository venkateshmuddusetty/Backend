package com.ibmw.userportal.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibmw.userportal.controller.UserSignupRequest;
import com.ibmw.userportal.dto.UserDetailsDto;
import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.exception.EntityType;
import com.ibmw.userportal.exception.ExceptionType;
import com.ibmw.userportal.exception.IBMUserPortalException;
import com.ibmw.userportal.mapper.UserMapper;
import com.ibmw.userportal.model.NewUser;
import com.ibmw.userportal.model.User;
import com.ibmw.userportal.model.UserProfile;
import com.ibmw.userportal.model.UserRoles;
import com.ibmw.userportal.repository.NewUserRepository;
import com.ibmw.userportal.repository.RoleRepository;
import com.ibmw.userportal.repository.UserProfileRepository;
import com.ibmw.userportal.repository.UserRepository;
import com.ibmw.userportal.response.MessageResponse;
import com.ibmw.userportal.service.UserProfileService;
import com.ibmw.userportal.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.ibmw.userportal.exception.ExceptionType.*;
import static com.ibmw.userportal.exception.EntityType.*;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NewUserRepository newUserRepo;

	@Autowired
	private UserProfileRepository userProfileRepo;

	@Autowired
	private UserProfileService userProfileService;

	@Override
	public UserProfileDto saveOrUpdateUser(UserProfileDto userProfileDto) {

		Optional<User> user = findUserByEmail(userProfileDto.getEmail());
		if (!user.isPresent()) {
			User newUser = modelMapper.map(userProfileDto, User.class);
			newUser.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByRole(UserRoles.USER))));
			newUser = userRepository.save(newUser);
			UserProfile userProfile = modelMapper.map(userProfileDto, UserProfile.class);
			userProfile.setUser(newUser);
			userProfile = userProfileRepo.save(userProfile);
			return modelMapper.map(userProfile, UserProfileDto.class);
		}
		throw exception(USER, DUPLICATE_ENTITY, userProfileDto.getEmail());
	}

	@Override
	public ResponseEntity<MessageResponse> signup(UserSignupRequest userSignUp) {

		Optional<User> userDb = findUserByEmail(userSignUp.getEmail());
		if (userDb.isPresent()) {

			User user = userDb.get();
			user.setPassword(bCryptPasswordEncoder.encode(userSignUp.getPassword()));
			user = userRepository.save(user);

			UserProfile userProfile = userProfileRepo.findByUser(user);
			userProfile.setFirstName(userSignUp.getFirstName());
			if (userSignUp.getMiddleName() != null) {
				userProfile.setMiddleName(userSignUp.getMiddleName());
			}
			userProfile.setLastName(userSignUp.getLastName());
			userProfileRepo.save(userProfile);

			return ResponseEntity.ok(new MessageResponse("Registered Successfully!"));

		}
		throw exception(USER, ENTITY_NOT_FOUND, userSignUp.getEmail());
	}

//	public UserDto findUserByEmail(String email) {
//		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
//
//		if (user.isPresent()) {
//			return modelMapper.map(user.get(), UserDto.class);
//		}
//		throw exception(USER, ENTITY_NOT_FOUND, email);
//	}

	@Override
	public UserDto updateProfile(UserDto userDto) {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (user.isPresent()) {
			User userModel = user.get();

			return UserMapper.toUserDto(userRepository.save(userModel));
		}
		throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
	}

	@Override
	public UserDto changePassword(UserDto userDto, String newPassword) {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (user.isPresent()) {
			User userModel = user.get();
			userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
			return UserMapper.toUserDto(userRepository.save(userModel));
		}
		throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
	}

	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		return IBMUserPortalException.throwException(entityType, exceptionType, args);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		UserProfile userProfile = userProfileRepo.findByUser(user);
		if (userProfile == null) {
			throw new UsernameNotFoundException("Invalid User Profile Data");
		}

		// UserProfile userProfile = modelMapper.map(userProfileDto, UserProfile.class);
		UserDetailsDto userDetailsDto = new UserDetailsDto(user.getId(), user.getEmail(), user.getPassword(), false,
				user.getLastLoggedIn(), user.getRoles(), userProfile);
		return UserDetailsImpl.build(userDetailsDto);

	}

	private Set getAuthority(User user) {
		Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		});
		return authorities;
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
		return user;
	}
}