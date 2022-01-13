package com.ibmw.userportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibmw.userportal.model.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Long> {

}
