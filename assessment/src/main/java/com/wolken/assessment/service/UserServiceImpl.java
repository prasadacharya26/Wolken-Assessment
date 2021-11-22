package com.wolken.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.assessment.dto.UserDTO;
import com.wolken.assessment.entity.UserEntity;
import com.wolken.assessment.entity.UserandTicketEntity;
import com.wolken.assessment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserandTicketService userandTicketService;
	
	@Override

	public String validateAndRegister(UserDTO userDTO) {
		UserEntity emailEntity = userRepository.findUserByEmail(userDTO.getEmail());
		UserEntity contactEnity = userRepository.findUserByContactNumber(userDTO.getContactNumber());
		try {
			UserEntity userEntity = new UserEntity();
			if (!userDTO.getFirstName().equals(null) && !userDTO.getFirstName().equals("")) {
				if (!userDTO.getLastName().equals(null) && !userDTO.getLastName().equals("")) {
					if (!userDTO.getEmail().equals(null) && !userDTO.getEmail().equals("")) {
						if (userDTO.getContactNumber() > 5999999999l && userDTO.getContactNumber() < 9999999999l) {
							if (!userDTO.getGender().equals(null) && !userDTO.getGender().equals("")) {
								if (!userDTO.getAddress().equals(null) && !userDTO.getAddress().equals("")) {
									if (!userDTO.getCity().equals(null) && !userDTO.getCity().equals("")) {
										if (!userDTO.getState().equals(null) && !userDTO.getState().equals("")) {
											if (!userDTO.getCountry().equals(null) && !userDTO.getCountry().equals("")) {
												if (userDTO.getPincode()!=0) {
													if(emailEntity==null){
														if(contactEnity==null) {
															if(userDTO.getGender().equalsIgnoreCase("male") || userDTO.getGender().equalsIgnoreCase("female") || userDTO.getGender().equalsIgnoreCase("others")) {
																BeanUtils.copyProperties(userDTO, userEntity);
																userRepository.save(userEntity);
																logger.info("Registered successfull");
																return "Registered successfull";
															}else {
																logger.info("Invalid gender");
																return "Invalid gender";
															}
														}else {
															logger.info(userDTO.getContactNumber()+" "+"this contact number already exists");
															return userDTO.getContactNumber()+"this contact number already exists";
														}
													}else {
														logger.info(userDTO.getEmail()+" "+"this email already exists");
														return userDTO.getEmail()+" "+"this email already exists";
													}
												} else {
													logger.info("Invalid pincode");
													return "Invalid pincode";
												}
											} else {
												logger.info("Invalid country");
												return "Invalid country";
											}
										} else {
											logger.info("Invalid state");
											return "Invalid state";
										}
									} else {
										logger.info("Invalid city");
										return "Invalid city";
									}
								} else {
									logger.info("Invalid address");
									return "Invalid address";
								}
							} else {
								logger.info("Invalid gender");
								return "Invalid gender";
							}
						} else {
							logger.info("Invalid contact number");
							return "Invalid contact numnber";
						}
					} else {
						logger.info("Invalid email");
						return "Invalid email";
					}
				} else {
					logger.info("Invalid last name");
					return "Invalid last name";
				}
			} else {
				logger.info("Invalid first name");
				return "Invalid first name";
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
			return e.getMessage();
		}
	}

	@Override
	public UserDTO validateAndgetUserByEmail(String email) {
		UserDTO userDTO = new UserDTO();
		try {
			if(email!=null) {
				UserEntity userEntity = userRepository.findUserByEmail(email);
				if (userEntity != null) {
					logger.info("" + userEntity);
					BeanUtils.copyProperties(userEntity, userDTO);
					logger.info("" + userEntity);
					logger.info("" + userDTO);
				} else {
					logger.info("Email not found");
				}
			}else {
				logger.info("Invalid email");
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
		}
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserDTO> userDTOs = new ArrayList<>();
		try {
			List<UserEntity> userEntities = userRepository.findAll();
			if(userEntities!=null) {
				for (UserEntity userEntity : userEntities) {
					UserDTO userDTO = new UserDTO();
					BeanUtils.copyProperties(userEntity, userDTO);
					userDTOs.add(userDTO);
				}
				logger.info("" + userDTOs);
			}else {
				logger.info("No Data Available");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
		}
		return userDTOs;
	}

	@Override
	public String updateUserDetails(UserDTO userDTO) {
		try {
			UserEntity userEntity = userRepository.findById(userDTO.getId());
			logger.info("" + userEntity);
			if (userEntity != null) {
				if (userDTO.getFirstName() != null) {
					userEntity.setFirstName(userDTO.getFirstName());
				}
				if (userDTO.getLastName() != null) {
					userEntity.setLastName(userDTO.getLastName());
				}
				if (userDTO.getEmail() != null) {
					userEntity.setEmail(userDTO.getEmail());
				}
				if (userDTO.getContactNumber() != 0) {
					if(userDTO.getContactNumber()>5999999999l && userDTO.getContactNumber()<9999999999l) {
						userEntity.setContactNumber(userDTO.getContactNumber());
					}else {
						logger.info("Invalid contact number");
						return "Invalid contact number";
					}
				}
				if (userDTO.getGender() != null) {
					if(userDTO.getGender().equalsIgnoreCase("male") || userDTO.getGender().equalsIgnoreCase("female") || userDTO.getGender().equalsIgnoreCase("other")) {
						userEntity.setGender(userDTO.getGender());
					}else {
						logger.info("Invalid gender");
						return "Invalid gender";
					}	
				}
				if (userDTO.getAddress() != null) {
					userEntity.setAddress(userDTO.getAddress());
				}
				if (userDTO.getCity() != null) {
					userEntity.setCity(userDTO.getCity());
				}
				if (userDTO.getState() != null) {
					userEntity.setState(userDTO.getState());
				}
				if (userDTO.getCountry() != null) {
					userEntity.setCountry(userDTO.getCountry());
				}
				if (userDTO.getPincode() != 0) {
					userEntity.setPincode(userDTO.getPincode());
				}
				logger.info(""+userEntity);
				userRepository.save(userEntity);
				List<UserandTicketEntity> mapping = userandTicketService.updateUserMapping(userEntity);
				logger.info("Data Updated");
				logger.info(""+mapping);
			} else {
				logger.info("User Id not found");
				return "User Id not found";
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
			return e.getMessage();
		}
		return "User data updated";
	}

}
