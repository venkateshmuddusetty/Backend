package com.ibmw.userportal.controller;

import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.model.Designation;
import com.ibmw.userportal.model.Skills;
import com.ibmw.userportal.response.MessageResponse;
import com.ibmw.userportal.response.Response;
import com.ibmw.userportal.service.DesignationService;
import com.ibmw.userportal.service.SkillService;
import com.ibmw.userportal.service.UserProfileService;
import com.ibmw.userportal.service.UserService;

@CrossOrigin(origins = "*", maxAge = 4200)
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	SkillService skillService;

	@Autowired
	DesignationService designationService;

	@Autowired
	UserProfileService userprofileservice;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping("/register/newUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserProfileDto userProfileDto) {
		userService.saveOrUpdateUser(userProfileDto);
		return ResponseEntity.ok(new MessageResponse("New User added successfully!"));
	}

	@PostMapping("/register/add/skills")
	public ResponseEntity<?> addSkillSets(@Valid @RequestBody Set<Skills> skillSets) {

		skillService.saveAll(skillSets);

		return ResponseEntity.ok(new MessageResponse("SkillSets added successfully!"));
	}

	@PostMapping("/register/add/designation")
	public ResponseEntity<?> addDesignation(@Valid @RequestBody Set<Designation> designation) {

		designationService.saveAll(designation);

		return ResponseEntity.ok(new MessageResponse("Designations added successfully!"));
	}

	@PutMapping("/user/updateuserprofile/{id}")
	public Response updateUseProfileById(@PathVariable long id, @RequestBody @Valid UserProfileDto userProfileDTo) {
		return Response.ok().setPayload(userprofileservice.updateUserProfileByID(id, userProfileDTo));
	}

}
