package com.wolken.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.assessment.dto.MappingUserandTicketDTO;
import com.wolken.assessment.entity.MappingUserandTicket;
import com.wolken.assessment.entity.TicketEntity;
import com.wolken.assessment.entity.UserEntity;
import com.wolken.assessment.repository.MappingUserandTicketRepository;




@Service
public class MappingUserandTicketServiceImpl implements MappingUserandTicketService {
	@Autowired
	MappingUserandTicketRepository mappingUserandTicketRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public String mapUserandTicket(UserEntity userEntity, TicketEntity ticketEntity) {
		try {
			MappingUserandTicket mappingUserandTicket =new MappingUserandTicket();
			mappingUserandTicket.setTicketEntity(ticketEntity);
			mappingUserandTicket.setUserEntity(userEntity);
			mappingUserandTicketRepository.save(mappingUserandTicket);
			logger.info(""+mappingUserandTicket);
			logger.info("maping saved");
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
			
		return "Data saved";
	}
	
	@Override
	public List<MappingUserandTicketDTO> getAllTicket() {
		List<MappingUserandTicketDTO> mappingUserandTickets = new ArrayList<>();
		
		try {
			List<MappingUserandTicket> mappingUserandTickets2 = mappingUserandTicketRepository.findAll();
			MappingUserandTicketDTO mappingUserandTicket = new MappingUserandTicketDTO();
			logger.info(""+mappingUserandTickets2);
			for (MappingUserandTicket mappingUserandTicket2 : mappingUserandTickets2) {
				BeanUtils.copyProperties(mappingUserandTicket2, mappingUserandTicket);
				logger.info(""+mappingUserandTicket);
				mappingUserandTickets.add(mappingUserandTicket);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return mappingUserandTickets;
	}

	@Override
	public List<MappingUserandTicket> getUserTicket(MappingUserandTicket mapUserandTicket) {
		List<MappingUserandTicket> mappingUserandTickets = new ArrayList<>();
		
		try {
			List<UserEntity> userEntities = mappingUserandTicketRepository.findByUserEntity(mapUserandTicket);
			MappingUserandTicket mappingUserandTicket = new MappingUserandTicket();
			for (UserEntity userEntity2 : userEntities) {
				BeanUtils.copyProperties(userEntity2, mappingUserandTicket);
				mappingUserandTickets.add(mappingUserandTicket);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return mappingUserandTickets;
	}

	

}
