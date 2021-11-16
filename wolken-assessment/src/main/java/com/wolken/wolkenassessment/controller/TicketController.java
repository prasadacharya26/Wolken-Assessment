package com.wolken.wolkenassessment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.wolkenassessment.dto.TicketDTO;
import com.wolken.wolkenassessment.service.TicketService;

@RestController
public class TicketController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping("saveTicket")
	String saveTicket(@RequestBody TicketDTO ticketDTO) {
		String message = null;
		try {
			message = ticketService.validateAndAddTicket(ticketDTO);
		} catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return message;
	}
	
	@GetMapping("allTicket")
	List<TicketDTO> allTicket(){
		List<TicketDTO> ticketDTOs = null;
		try {
			ticketDTOs = ticketService.validateAndGetAll();
		} catch (Exception e) {
			logger.info(e.getMessage(),e.getClass().getSimpleName());
		}
		return ticketDTOs;
	}
	
	@PostMapping("updateTicket")
	String updateTicket(@RequestBody TicketDTO ticketDTO) {
		String message = null;
		try {
			message = ticketService.updateTicketById(ticketDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return message;
	}
}
