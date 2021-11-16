package com.wolken.wolkenassessment.service;

import java.util.List;

import com.wolken.wolkenassessment.dto.UserandTicketDTO;
import com.wolken.wolkenassessment.entity.TicketEntity;
import com.wolken.wolkenassessment.entity.UserEntity;
import com.wolken.wolkenassessment.entity.UserandTicketEntity;

public interface UserandTicketService {
	String mappingUserandTicket(UserEntity userEntity,TicketEntity ticketEntity);
	
	String updateMapping(TicketEntity ticketEntity);
	
	List<UserandTicketEntity> updateUserMapping (UserEntity userEntity);
	
	List<UserandTicketDTO> getAllTicket();
	
	List<UserandTicketDTO> getUserTicket(int customerId);
}
