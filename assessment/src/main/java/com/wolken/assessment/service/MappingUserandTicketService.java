package com.wolken.assessment.service;

import java.util.List;

import com.wolken.assessment.dto.MappingUserandTicketDTO;
import com.wolken.assessment.entity.MappingUserandTicket;
import com.wolken.assessment.entity.TicketEntity;
import com.wolken.assessment.entity.UserEntity;


public interface MappingUserandTicketService {
	String mapUserandTicket(UserEntity userEntity,TicketEntity ticketEntity);
	

	List<MappingUserandTicketDTO> getAllTicket();


	List<MappingUserandTicket> getUserTicket(MappingUserandTicket mappingUserandTicket);


}
