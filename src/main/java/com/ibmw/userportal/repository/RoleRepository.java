package com.ibmw.userportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibmw.userportal.model.Role;
import com.ibmw.userportal.model.UserRoles;

public interface RoleRepository extends CrudRepository<Role,Long>
{
    Role findByRole(UserRoles role);
}
