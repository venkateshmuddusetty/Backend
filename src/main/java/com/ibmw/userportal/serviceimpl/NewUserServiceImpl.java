package com.ibmw.userportal.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibmw.userportal.exception.EntityType;
import com.ibmw.userportal.exception.ExceptionType;
import com.ibmw.userportal.exception.IBMUserPortalException;
import com.ibmw.userportal.model.NewUser;
import com.ibmw.userportal.repository.NewUserRepository;
import com.ibmw.userportal.service.NewUserService;

@Component
public class NewUserServiceImpl implements NewUserService {
	@Autowired
	NewUserRepository newUserRepo;

	@Override
	public NewUser save(NewUser newUser) {
		// TODO Auto-generated method stub
		return newUserRepo.save(newUser);
	}
	
	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		return IBMUserPortalException.throwException(entityType, exceptionType, args);
	}
}
