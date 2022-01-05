package com.watson.springjwt.models;
/*
 * package com.bezkoder.springjwt.models;
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * import javax.persistence.*; import javax.validation.constraints.Email; import
 * javax.validation.constraints.NotBlank; import
 * javax.validation.constraints.Size; //import
 * org.springframework.data.annotation.Id;
 * 
 * @Entity
 * 
 * @Table(name = "userinfo", catalog = "testdb") public class UserInfo {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private String id;
 * 
 * @NotBlank
 * 
 * @Size(max = 20) private String firstName;
 * 
 * @Size(max = 20) private String middleName;
 * 
 * @NotBlank
 * 
 * @Size(max = 20) private String lastName;
 * 
 * @NotBlank
 * 
 * @Size(max = 50)
 * 
 * @Email private String email;
 * 
 * @NotBlank
 * 
 * @Size(max = 7) private int empId;
 * 
 * @NotBlank
 * 
 * @Size(max = 50) private String gender;
 * 
 * @NotBlank
 * 
 * @Size(max = 50) private Designation designation;
 * 
 * @NotBlank
 * 
 * @Size(max = 10) private long mobileNo;
 * 
 * 
 * private Location location;
 * 
 * private Set<TechRole> techRoles = new HashSet<>();
 * 
 * 
 * private Set<Project> projects = new HashSet<>();
 * 
 * 
 * private Set<SkillSet> skillSets = new HashSet<>();
 * 
 * public UserInfo() {
 * 
 * }
 * 
 * public UserInfo(@NotBlank @Size(max = 20) String firstName, @Size(max = 20)
 * String middleName,
 * 
 * @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 50) @Email
 * String email,
 * 
 * @NotBlank @Size(max = 7) int empId, String gender, @NotBlank @Size(max = 120)
 * Designation designation,
 * 
 * @NotBlank @Size(max = 10) long mobileNo, Location location, Set<TechRole>
 * techRoles, Set<Project> projects, Set<SkillSet> skillSets) { this.firstName =
 * firstName; this.middleName = middleName; this.lastName = lastName; this.email
 * = email; this.empId = empId; this.gender = gender; this.designation =
 * designation; this.mobileNo = mobileNo; this.location = location;
 * this.techRoles = techRoles; this.projects = projects; this.skillSets =
 * skillSets; }
 * 
 * // UserInfo Registraion public UserInfo(@NotBlank @Size(max = 20) String
 * firstName, @Size(max = 20) String middleName,
 * 
 * @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 50) @Email
 * String email,
 * 
 * @NotBlank @Size(max = 7) int empId) { this.firstName = firstName;
 * this.middleName = middleName; this.lastName = lastName; this.email = email;
 * this.empId = empId; }
 * 
 * public String getId() { return id; }
 * 
 * public void setId(String id) { this.id = id; }
 * 
 * public String getFirstName() { return firstName; }
 * 
 * public void setFirstName(String firstName) { this.firstName = firstName; }
 * 
 * public String getMiddleName() { return middleName; }
 * 
 * public void setMiddleName(String middleName) { this.middleName = middleName;
 * }
 * 
 * public String getLastName() { return lastName; }
 * 
 * public void setLastName(String lastName) { this.lastName = lastName; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * public int getEmpId() { return empId; }
 * 
 * public void setEmpId(int empId) { this.empId = empId; }
 * 
 * public String getGender() { return gender; }
 * 
 * public void setGender(String gender) { this.gender = gender; }
 * 
 * public Designation getDesignation() { return designation; }
 * 
 * public void setDesignation(Designation designation) { this.designation =
 * designation; }
 * 
 * public long getMobileNo() { return mobileNo; }
 * 
 * public void setMobileNo(long mobileNo) { this.mobileNo = mobileNo; }
 * 
 * public Location getLocation() { return location; }
 * 
 * public void setLocation(Location location) { this.location = location; }
 * 
 * public Set<TechRole> getTechRoles() { return techRoles; }
 * 
 * public void setTechRoles(Set<TechRole> techRoles) { this.techRoles =
 * techRoles; }
 * 
 * public Set<Project> getProjects() { return projects; }
 * 
 * public void setProjects(Set<Project> projects) { this.projects = projects; }
 * 
 * public Set<SkillSet> getSkillSets() { return skillSets; }
 * 
 * public void setSkillSets(Set<SkillSet> skillSets) { this.skillSets =
 * skillSets; }
 * 
 * @Override public String toString() { return "UserInfo [id=" + id +
 * ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" +
 * lastName + ", email=" + email + ", empId=" + empId + ", gender=" + gender +
 * ", designation=" + designation + ", mobileNo=" + mobileNo + ", location=" +
 * location + ", techRoles=" + techRoles + ", projects=" + projects +
 * ", skillSets=" + skillSets + "]"; }
 * 
 * }
 */