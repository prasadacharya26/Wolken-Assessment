package com.wolken.assessment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.assessment.dto.MappingUserandTicketDTO;
import com.wolken.assessment.entity.MappingUserandTicket;
import com.wolken.assessment.service.MappingUserandTicketService;


@RestController
public class MappingUserandTicketController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	MappingUserandTicketService mappingUserandTicketService;
	
	@GetMapping("allmaptickets")
	List<MappingUserandTicketDTO> allmaptickets(){
		List<MappingUserandTicketDTO> userandTicketDTOs=null;
		try {
			userandTicketDTOs = mappingUserandTicketService.getAllTicket();
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return userandTicketDTOs;
	}
	
	@PostMapping("usermaptickets")
	List<MappingUserandTicket> usermaptickets(MappingUserandTicket mappingUserandTicket){
		List<MappingUserandTicket> mappingUserandTickets=null;
		try {
			mappingUserandTickets = mappingUserandTicketService.getUserTicket(mappingUserandTicket);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return mappingUserandTickets;
	}
	
}
