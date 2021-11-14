package com.wolken.bankDetails.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.stereotype.Service;

import com.wolken.bankDetails.dto.EmployeeDTO;
import com.wolken.bankDetails.entity.EmployeeEntity;
import com.wolken.bankDetails.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository repo;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<EmployeeDTO> getEmployeeDetails() {
		List<EmployeeDTO> empdto = new ArrayList<>();
		try {
			List<EmployeeEntity> list = repo.findAll();
			logger.info("List" + list);
			for (EmployeeEntity employeeEntity : list) {
				EmployeeDTO dto = new EmployeeDTO();
				BeanUtils.copyProperties(employeeEntity, dto);
				empdto.add(dto);
				logger.info("DTO" + dto);
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e.getClass().getSimpleName());
		}
		return empdto;
	}

	@Override
	public String valiadteAndSave(EmployeeDTO dto) {
		try {
			if (dto != null) {
				if (!dto.getName().equals(null) && !dto.getName().equals("")) {
					if (!dto.getEmail().equals(null) && !dto.getEmail().equals("")) {
						if (!dto.getDesignation().equals(null) && !dto.getDesignation().equals("")) {
							if (dto.getContactNumber() > 5999999999l && dto.getContactNumber() < 9999999999l) {
								if (dto.getSalary() > 10000) {
									logger.info("" + dto);
									EmployeeEntity entity = new EmployeeEntity();
									BeanUtils.copyProperties(dto, entity);
									repo.save(entity);
									logger.info("Data saved");
									return "Data saved";
								} else {
									logger.info("invalid salary");
									return "Invalid salary";
								}
							} else {
								logger.info("invalid contact number");
								return "Invalid contact number";
							}
						} else {
							logger.info("invalid designation");
							return "Invalid designation";
						}
					} else {
						logger.info("invalid email");
						return "Invalid email";
					}
				} else {
					logger.info("invalid name");
					return "Invalid name";
				}
			} else {
				logger.info("object is null");
				return null;
			}
		} catch (NullPointerException | TypeMismatchDataAccessException e) {
			logger.info(e.getMessage(), e.getClass().getSimpleName());
		}
		return null;
	}

	@Override
	public List<EmployeeDTO> validateAndFindByName(String name) {
		List<EmployeeDTO> empdto = new ArrayList<>();
		List<EmployeeEntity> list = repo.findByName(name);
		logger.info("List" + list);
		for (EmployeeEntity employeeEntity : list) {
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(employeeEntity, dto);
			empdto.add(dto);
			logger.info("DTO" + dto);
		}
		return empdto;
	}

	@Override
	public List<EmployeeDTO> validateAndFindByDesignation(String designation) {
		List<EmployeeDTO> empdto = new ArrayList<>();
		try {
			List<EmployeeEntity> list = repo.findByDesignation(designation);
			logger.info("List" + list);
			for (EmployeeEntity employeeEntity : list) {
				EmployeeDTO dto = new EmployeeDTO();
				BeanUtils.copyProperties(employeeEntity, dto);
				empdto.add(dto);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.info(e.getMessage(), e.getClass().getSimpleName());
		}
		logger.info("" + empdto);
		return empdto;
	}

	@Override
	public List<EmployeeDTO> validateAndFindBySalary(float salary) {
		List<EmployeeDTO> empdto = new ArrayList<>();
		try {
			List<EmployeeEntity> list = repo.findBySalary(salary);
			logger.info("List" + list);
			for (EmployeeEntity employeeEntity : list) {
				EmployeeDTO dto = new EmployeeDTO();
				BeanUtils.copyProperties(employeeEntity, dto);
				empdto.add(dto);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.info(e.getMessage(), e.getClass().getSimpleName());
		}
		logger.info("" + empdto);
		return empdto;
	}

	@Override
	public String valiadteAndSaveAll(List<EmployeeDTO> dtos) {
		List<EmployeeEntity> entityAll = new ArrayList<EmployeeEntity>();
		try {
			if (dtos != null) {
				for (EmployeeDTO dto : dtos) {
					if (!dto.getName().equals(null) && !dto.getName().equals("")) {
						if (!dto.getEmail().equals(null) && !dto.getEmail().equals("")) {
							if (!dto.getDesignation().equals(null) && !dto.getDesignation().equals("")) {
								if (dto.getContactNumber() > 5999999999l && dto.getContactNumber() < 9999999999l) {
									if (dto.getSalary() > 1000) {
										EmployeeEntity entity = new EmployeeEntity();
										BeanUtils.copyProperties(dto, entity);
										entityAll.add(entity);
									} else {
										logger.info("invalid salary");
										return "Invalid salary";
									}
								} else {
									logger.info("invalid contact number");
									return "Invalid contact number";
								}
							} else {
								logger.info("invalid designation");
								return "Invalid designation";
							}
						} else {
							logger.info("invalid email");
							return "Invalid email";
						}
					} else {
						logger.info("invalid name");
						return "Invalid name";
					}
				}
				logger.info("" + entityAll);
				repo.saveAll(entityAll);
				logger.info("Data saved");
				return "Data Saved";
			} else {
				logger.info("Object is null");
				return "Object is null";
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.info(e.getMessage(), e.getClass().getSimpleName());
		}
		return null;
	}

}
