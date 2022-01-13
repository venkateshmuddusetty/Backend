package com.ibmw.userportal.dto;

import java.util.Collection;

import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ibmw.userportal.model.Designation;
import com.ibmw.userportal.model.Location;
import com.ibmw.userportal.model.Project;
import com.ibmw.userportal.model.Skills;
import com.ibmw.userportal.model.TechRoles;
import com.ibmw.userportal.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileDto {
	private String email;
	private String password;
	private boolean isAdmin;
	private Collection<RoleDto> roles;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private int empId;
	private String gender;
	private Long mobileNumber;
	private Collection<Skills> skillSets;
	private Collection<Project> projects;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Designation designation;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Location location;
	private Collection<TechRoles> techRoles;
}
