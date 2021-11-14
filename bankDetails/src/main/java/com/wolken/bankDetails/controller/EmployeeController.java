package com.wolken.bankDetails.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.bankDetails.dto.EmployeeDTO;
import com.wolken.bankDetails.service.EmployeeService;

@RestController
public class EmployeeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeService service;
	
	@PostMapping("save")
	String save(@RequestBody EmployeeDTO dto) {
		String msg=null;
		try {
			msg = service.valiadteAndSave(dto);
		}catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return msg;
	}
	
	@PostMapping("saveAll")
	String saveAll(@RequestBody List<EmployeeDTO> dto) {
		String msg=null;
		try {
			msg = service.valiadteAndSaveAll(dto);
		}catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return msg;
	}
	
	@GetMapping("getDetails")
	List<EmployeeDTO> getDetails() {
		List<EmployeeDTO> list = null;
		try {
			list=service.getEmployeeDetails();
		} catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return list;
	}
	
	@GetMapping("getByDesignation")
	List<EmployeeDTO> getByDesignation(@RequestParam String designation) {
		List<EmployeeDTO> dto = null;
		try {
			dto=service.validateAndFindByDesignation(designation);
		} catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return dto;
	}
	
	@GetMapping("getBySalary")
	List<EmployeeDTO> getBySalary(@RequestParam float salary) {
		List<EmployeeDTO> dto = null;
		try {
			dto=service.validateAndFindBySalary(salary);
		} catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return dto;
	}
}
