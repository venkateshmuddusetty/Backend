package com.ibmw.userportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.model.UserProfile;


public interface UserProfileService 
{
	public List<UserProfileDto> getAllUsers();
	
	public UserProfileDto updateUserProfileByID(Long id,UserProfileDto userProfileDto);
	public UserProfileDto  findByUserId(Long id);
	public ResponseEntity<Map<String,Object>> getAllUsersWithpaginationFilters(String projects, int page, int size);
}
