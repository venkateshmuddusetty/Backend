package com.ibmw.userportal.service;

import java.util.Set;

import com.ibmw.userportal.model.Skills;

public interface SkillService {

	Set<Skills> saveAll(Set<Skills> skillSets);
}
