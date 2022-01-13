package com.ibmw.userportal.serviceimpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmw.userportal.model.Designation;
import com.ibmw.userportal.model.Skills;
import com.ibmw.userportal.repository.DesignationRepository;
import com.ibmw.userportal.repository.SkillsRepository;
import com.ibmw.userportal.service.DesignationService;
import com.ibmw.userportal.service.SkillService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesignationRepository designationRepo;


	@Override
	public Set<Designation> saveAll(Set<Designation> designationSet) {
		// TODO Auto-generated method stub
		List<Designation> savedResponse = designationRepo.saveAll(new LinkedList<>(designationSet));
		return new HashSet<Designation>(savedResponse);
	}

	
	

}
