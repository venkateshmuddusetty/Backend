package com.ibmw.userportal.payload.responses;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ibmw.userportal.dto.UserDetailsDto;
import com.ibmw.userportal.model.Role;
import com.ibmw.userportal.model.UserProfile;
import com.ibmw.userportal.serviceimpl.UserDetailsImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@ToString
@DynamicUpdate
public class JwtResponse {
	private String token;
//	private String type = "Bearer";
	private Long id;
	private String username;
	private Date lastLoggedIn;
	private UserProfile userInfo;
	private List<String> roles;
	

}
