package com.ibmw.userportal.service;

import java.util.Set;

import com.ibmw.userportal.model.Designation;

public interface DesignationService {
	Set<Designation> saveAll(Set<Designation> skillSets);
}
