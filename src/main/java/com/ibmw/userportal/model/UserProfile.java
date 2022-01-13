package com.ibmw.userportal.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "emp_id")
	private int empId;
	
	@Column(name = "email")
	private String email;

	@Column(name = "gender")
	private String gender;

	@Column(name = "mobile_number")
	private Long mobileNumber;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true)
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_skills", joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "skillset_id") })
	private Collection<Skills> skillSets;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_project", joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "project_id") })
	private Collection<Project> projects;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_designation", joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "designation_id") })
	private Designation designation;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_location", joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "location_id") })
	private Location location;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_techrole", joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "techrole_id") })
	private Collection<TechRoles> techRoles;

}
