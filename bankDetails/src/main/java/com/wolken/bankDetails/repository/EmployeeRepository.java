package com.wolken.bankDetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.bankDetails.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepositoryImplementation<EmployeeEntity, Integer>{
	List<EmployeeEntity> findByName(String name);
	List<EmployeeEntity> findByDesignation(String designation);
	List<EmployeeEntity> findBySalary(float salary);
	EmployeeEntity findById(int id);
}
