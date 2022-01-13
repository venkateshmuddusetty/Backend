package com.ibmw.userportal.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {
	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private String firstName;
	
	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private String middleName;

	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private String lastName;
	
	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private String empId;

	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private String email;

	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private String password;

}
