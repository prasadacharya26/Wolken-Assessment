package com.wolken.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.assessment.dto.UserandTicketDTO;
import com.wolken.assessment.entity.TicketEntity;
import com.wolken.assessment.entity.UserEntity;
import com.wolken.assessment.entity.UserandTicketEntity;
import com.wolken.assessment.repository.UserRepository;
import com.wolken.assessment.repository.UserandTicketRepository;

@Service
public class UserandTicketServiceImpl implements UserandTicketService {
	@Autowired
	UserandTicketRepository userandTicketRepository;

	@Autowired
	UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String mappingUserandTicket(UserEntity userEntity, TicketEntity ticketEntity) {
		try {
			UserandTicketEntity userandTicketEntity = new UserandTicketEntity();
			BeanUtils.copyProperties(ticketEntity, userandTicketEntity);
			BeanUtils.copyProperties(userEntity, userandTicketEntity);
			userandTicketRepository.save(userandTicketEntity);
			logger.info("" + userandTicketEntity);
			logger.info("maping saved");
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
			return e.getMessage();
		}
		return "saved";
	}

	@Override
	public String updateMapping(TicketEntity ticketEntity) {
		UserandTicketEntity userandTicketEntity = userandTicketRepository.findById(ticketEntity.getTicketId());
		try {
			if (userandTicketEntity != null) {
				BeanUtils.copyProperties(ticketEntity, userandTicketEntity);
				userandTicketRepository.save(userandTicketEntity);
				logger.info("" + userandTicketEntity);
				logger.info("maping saved");
				return "Data saved";
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
			return e.getMessage();
		}
		return null;
	}

	@Override
	public List<UserandTicketEntity> updateUserMapping(UserEntity userEntity) {
		List<UserandTicketEntity> userandTicketEntity = userandTicketRepository.findBycustomerId(userEntity.getId());
		List<UserandTicketEntity> userandTicketEntities = new ArrayList<>();
		try {
			if (userandTicketEntity != null) {
				logger.info("a" + userEntity);
				for (UserandTicketEntity userandTicketEntity2 : userandTicketEntity) {
					BeanUtils.copyProperties(userEntity, userandTicketEntity2);
					logger.info("" + userandTicketEntity2);
					userandTicketEntities.add(userandTicketEntity2);
					logger.info("" + userandTicketEntities + "\n");
					logger.info("maping saved");
				}
				userandTicketRepository.saveAll(userandTicketEntities);
				logger.info("" + userandTicketEntities);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
		}
		return userandTicketEntity;
	}

	@Override
	public List<UserandTicketDTO> getAllTicket() {
		List<UserandTicketDTO> userandTicketDTOs = new ArrayList<>();
		try {
			List<UserandTicketEntity> userandTicketEntities = userandTicketRepository.findAll();

			for (UserandTicketEntity userandTicketEntity : userandTicketEntities) {
				UserandTicketDTO userandTicketDTO = new UserandTicketDTO();
				BeanUtils.copyProperties(userandTicketEntity, userandTicketDTO);
				userandTicketDTOs.add(userandTicketDTO);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getName());
		}
		return userandTicketDTOs;
	}

	@Override
	public List<UserandTicketDTO> getUserTicket(int customerId) {
		List<UserandTicketDTO> userandTicketDTOs = new ArrayList<>();
		try {
			List<UserandTicketEntity> userandTicketEntities = userandTicketRepository.findBycustomerId(customerId);
			for (UserandTicketEntity userandTicketEntity : userandTicketEntities) {
				UserandTicketDTO userandTicketDTO = new UserandTicketDTO();
				BeanUtils.copyProperties(userandTicketEntity, userandTicketDTO);
				userandTicketDTOs.add(userandTicketDTO);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getName());
		}
		return userandTicketDTOs;
	}

	@Override
	public String mappingUserandTicket(List<TicketEntity> ticketEntitie) {
		try {
			
			for (TicketEntity ticketEntity : ticketEntitie) {
				UserandTicketEntity userandTicketEntity = new UserandTicketEntity();
				UserEntity userEntity = userRepository.findById(ticketEntity.getCustomerId());
				BeanUtils.copyProperties(ticketEntity, userandTicketEntity);
				BeanUtils.copyProperties(userEntity, userandTicketEntity);
				userandTicketRepository.save(userandTicketEntity);
				logger.info("" + userandTicketEntity);
			}
			logger.info("maping saved");

		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
		}
		return null;
	}
}
