package com.ibmw.userportal.serviceimpl;

import static com.ibmw.userportal.exception.EntityType.*;
import static com.ibmw.userportal.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ibmw.userportal.dto.UserDto;
import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.exception.EntityType;
import com.ibmw.userportal.exception.ExceptionType;
import com.ibmw.userportal.exception.IBMUserPortalException;
import com.ibmw.userportal.mapper.UserMapper;
import com.ibmw.userportal.mapper.UserProfileMapper;
import com.ibmw.userportal.model.NewUser;
import com.ibmw.userportal.model.User;
import com.ibmw.userportal.model.UserProfile;
import com.ibmw.userportal.model.UserRoles;
import com.ibmw.userportal.repository.RoleRepository;
import com.ibmw.userportal.repository.UserProfileRepository;
import com.ibmw.userportal.repository.UserRepository;
import com.ibmw.userportal.response.MessageResponse;
import com.ibmw.userportal.service.UserProfileService;
import com.ibmw.userportal.service.UserService;

@Component
public class UserProfileServiceImpl implements UserProfileService

{
	@Autowired
	UserProfileRepository userprofilerepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserProfileDto> getAllUsers() 
	{

		return StreamSupport.stream(userprofilerepository.findAll().spliterator(), false)
				.map(user -> modelMapper.map(user, UserProfileDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserProfileDto updateUserProfileByID(Long id, UserProfileDto userProfileDto) {

		Optional<UserProfile> profileuser = Optional.ofNullable(userprofilerepository.findByUserId(id));
		if (profileuser.isPresent()) {
			System.out.println("Projects from " + userProfileDto.getProjects());
			UserProfile userModel = profileuser.get();
			userModel.setGender(userProfileDto.getGender())
			.setEmpId(userProfileDto.getEmpId())
			.setDesignation(userProfileDto.getDesignation())
			.setGender(userProfileDto.getGender())
			.setLocation(userProfileDto.getLocation())
			.setSkillSets(userProfileDto.getSkillSets())
			.setProjects(userProfileDto.getProjects())
			.setTechRoles(userProfileDto.getTechRoles())
			.setMobileNumber(userProfileDto.getMobileNumber());
			
			// return UserMapper.toUserDto(userRepository.save(userModel));
			return UserProfileMapper.toUserDto(userprofilerepository.save(userModel));
		}
		throw exception(USER, ENTITY_NOT_FOUND);
	}

	@Transactional
	public UserProfileDto findByUserId(Long id) {
		Optional<Optional<UserProfile>> profileuser = Optional.ofNullable(userprofilerepository.findById(id));

		if (profileuser.isPresent()) {
			return modelMapper.map(profileuser.get(), UserProfileDto.class);
		}
		// throw exception(USERPROFILE, ENTITY_NOT_FOUND,"User not found");
		return null;
	}
	
	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		return IBMUserPortalException.throwException(entityType, exceptionType, args);

	}

	@Override
	public ResponseEntity<Map<String,Object>> getAllUsersWithpaginationFilters(String projects, int page, int size)
	{
		try 
		{
		List <UserProfile> profileusers= new ArrayList<UserProfile>();
		Pageable paging= PageRequest.of(page, size);
		
		Page<UserProfile> pageProfs;
		
		if(projects ==null)
			pageProfs=userprofilerepository.findAll(paging);
		else 
			pageProfs=userprofilerepository.findByprojectsContaining(projects, paging);
		
		profileusers= pageProfs.getContent();
		
		Map<String,Object> response = new HashMap<>();
		response.put("UserInfo", profileusers);
		response.put("CurrentPage", pageProfs.getNumber());
		response.put("TotalItems", pageProfs.getTotalElements());
		response.put("TotalPages",pageProfs.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	
}
