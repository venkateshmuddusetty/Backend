package com.ibmw.userportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ibmw.userportal.model.Role;
import com.ibmw.userportal.model.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto {
	private Long id;
    private String email;
    private String password;
    private boolean isAdmin;
    private Date lastLoggedIn;
    private Collection<Role> roles;
    private UserProfile userProfile;
}
