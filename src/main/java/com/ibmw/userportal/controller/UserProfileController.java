package com.ibmw.userportal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.model.UserProfile;
import com.ibmw.userportal.response.Response;
import com.ibmw.userportal.service.UserProfileService;

@CrossOrigin(origins = "*", maxAge = 4200)
@RestController
@RequestMapping("/home")
public class UserProfileController
{
	@Autowired
	UserProfileService userprofileservice;
	
	
	
	@GetMapping("/user/getusers")
    public Response getAllusers()
    {
    	return  Response.ok().setPayload(userprofileservice.getAllUsers());
    }
	
	@PutMapping("/user/updateuserprofile")
    public Response updateUseProfileById(long id, UserProfileDto userProfileDTo)
    {
    	return  Response.ok().setPayload(null);
    }
	
	@GetMapping("/user/getuserswithpagination")
    public ResponseEntity<Map<String, Object>> getAllusersWithPagination(
    		@RequestParam(required =false) String projects,
            @RequestParam(defaultValue ="0") int page,
            @RequestParam(defaultValue ="3") int size)
    {
		
		
		return userprofileservice.getAllUsersWithpaginationFilters(projects, page, size);
    }
	
	
}
