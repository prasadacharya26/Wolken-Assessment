package com.wolken.wolkenassessment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.wolkenassessment.dto.UserDTO;
import com.wolken.wolkenassessment.service.UserService;

@RestController
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService service;
	
	@PostMapping("registration")
	String registration(@RequestBody UserDTO userDTO) {
		String message = null;
		try {
			message = service.validateAndRegister(userDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return message;
	}
	
	@GetMapping("getUser")
	UserDTO getUser(@RequestParam String email) {
		UserDTO userDTO = null;
		try {
			userDTO = service.validateAndgetUserByEmail(email);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		logger.info(""+userDTO);
		return userDTO;
	}
	
	@GetMapping("getUserID")
	UserDTO getUserID(@RequestParam int id) {
		UserDTO userDTO = null;
		try {
			userDTO = service.validateAndgetUserById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		logger.info(""+userDTO);
		return userDTO;
	}
	
	@GetMapping("getAll")
	List<UserDTO> getAll(){
		List<UserDTO> userDTOs = null;
		try {
			userDTOs = service.getAllUser();
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return userDTOs;
	}
	
	@PostMapping("updateUser")
	String upadateUser(@RequestBody UserDTO userDTO) {
		String message = null;
		try {
			message = service.updateUserDetails(userDTO);
		} catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return message;
	}
	
	
}
