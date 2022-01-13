package com.ibmw.userportal.dto;

import java.util.Collection;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@DynamicUpdate
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillsDto 
{
	private String technology;
	private String skillCategory;

}
