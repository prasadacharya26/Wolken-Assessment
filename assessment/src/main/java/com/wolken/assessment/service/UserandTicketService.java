package com.wolken.assessment.service;

import java.util.List;

import com.wolken.assessment.dto.UserandTicketDTO;
import com.wolken.assessment.entity.TicketEntity;
import com.wolken.assessment.entity.UserEntity;
import com.wolken.assessment.entity.UserandTicketEntity;


public interface UserandTicketService {
	String mappingUserandTicket(UserEntity userEntity,TicketEntity ticketEntity);
	
	String updateMapping(TicketEntity ticketEntity);
	
	List<UserandTicketEntity> updateUserMapping (UserEntity userEntity);
	
	List<UserandTicketDTO> getAllTicket();
	
	List<UserandTicketDTO> getUserTicket(int customerId);

	String mappingUserandTicket(List<TicketEntity> ticketEntities);
}
