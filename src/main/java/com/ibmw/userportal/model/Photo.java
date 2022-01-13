package com.ibmw.userportal.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name="Photo")
@Table(name = "Photo")
public class Photo
{ 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	
	private String type;
	
	@Lob
	private byte[] data;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, orphanRemoval = true, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", unique = true)
	private User user;

	public Photo(String name, String type, byte[] data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}
	
	

	
	
	
	
	
	
	
	

}
