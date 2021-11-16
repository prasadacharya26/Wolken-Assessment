package com.wolken.wolkenassessment.service;

import java.util.List;

import com.wolken.wolkenassessment.dto.UserDTO;

public interface UserService {

	String validateAndRegister(UserDTO userDTO);

	UserDTO validateAndgetUserByEmail(String email);

	List<UserDTO> getAllUser();

	String updateUserDetails(UserDTO userDTO);


}
