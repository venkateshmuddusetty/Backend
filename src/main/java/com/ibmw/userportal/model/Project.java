package com.ibmw.userportal.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "Project")
public class Project {
	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectID;

	@Column(name = "project_name")
	private String pname;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private boolean status;

	
	  @Column(name = "start_date") 
	  private Date startdate;
	  
	  @Column(name = "end_date") 
	  private Date enddate;
	 

}
