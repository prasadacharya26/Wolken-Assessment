package com.wolken.bankDetails.service;

import java.util.List;

import com.wolken.bankDetails.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getEmployeeDetails();


	String valiadteAndSave(EmployeeDTO dto);


	List<EmployeeDTO> validateAndFindByName(String name);


	List<EmployeeDTO> validateAndFindByDesignation(String designation);


	List<EmployeeDTO> validateAndFindBySalary(float salary);


	String valiadteAndSaveAll(List<EmployeeDTO> dto);
	
}
