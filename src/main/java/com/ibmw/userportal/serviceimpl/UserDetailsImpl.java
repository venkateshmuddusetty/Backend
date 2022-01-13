package com.ibmw.userportal.serviceimpl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibmw.userportal.dto.UserDetailsDto;
import com.ibmw.userportal.model.UserProfile;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private Date lastLoggedIn;

	private UserProfile userProfile;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String password, Date lastLoggedIn, UserProfile userProfile,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastLoggedIn = lastLoggedIn;
		this.userProfile = userProfile;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UserDetailsDto userDetailDto) {
		List<GrantedAuthority> authorities = userDetailDto.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(userDetailDto.getId(), userDetailDto.getEmail(), userDetailDto.getPassword(), userDetailDto.getLastLoggedIn(),
				userDetailDto.getUserProfile(),authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}