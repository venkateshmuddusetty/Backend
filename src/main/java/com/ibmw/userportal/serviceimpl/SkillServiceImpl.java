package com.ibmw.userportal.serviceimpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmw.userportal.model.Skills;
import com.ibmw.userportal.repository.SkillsRepository;
import com.ibmw.userportal.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	SkillsRepository skillsRepo;

	@Override
	public Set<Skills> saveAll(Set<Skills> skillSets) {
		// TODO Auto-generated method stub

		List<Skills> savedResponse = skillsRepo.saveAll(new LinkedList<>(skillSets));
		return new HashSet<Skills>(savedResponse);
	}

	
	

}
