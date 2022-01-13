package com.ibmw.userportal.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibmw.userportal.model.NewUser;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser,Long> {

	Optional<NewUser> findByEmail(String email);
	
//	@Modifying
//	@Query("update NewUser nu set nu.user_id = ?1 where nu.id = ?2")
//	int updateUser(Long uid, Long newUserId);
}
