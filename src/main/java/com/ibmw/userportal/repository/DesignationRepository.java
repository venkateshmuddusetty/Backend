package com.ibmw.userportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibmw.userportal.model.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {

}
