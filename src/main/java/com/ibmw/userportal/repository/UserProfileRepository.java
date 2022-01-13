package com.ibmw.userportal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ibmw.userportal.dto.UserProfileDto;
import com.ibmw.userportal.model.User;
import com.ibmw.userportal.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> 
{
	UserProfile  findByUserId(Long id);
	UserProfile findByUser(User user);
	Page<UserProfile> findByprojectsContaining(String project, Pageable pageable);
}
