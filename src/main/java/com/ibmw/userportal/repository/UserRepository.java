package com.ibmw.userportal.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ibmw.userportal.model.User;



public interface UserRepository extends CrudRepository<User,Long> 
{
    User findByEmail(String email);
}
