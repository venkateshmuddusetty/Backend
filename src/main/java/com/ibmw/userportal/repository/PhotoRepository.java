package com.ibmw.userportal.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibmw.userportal.model.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long> 
{

	Optional<Photo> findByUserId(Long id);
	
}
