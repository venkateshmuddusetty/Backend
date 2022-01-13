package com.ibmw.userportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@DynamicUpdate
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String email;
    private String password;
    private boolean isAdmin;
    private Collection<RoleDto> roles;
}
