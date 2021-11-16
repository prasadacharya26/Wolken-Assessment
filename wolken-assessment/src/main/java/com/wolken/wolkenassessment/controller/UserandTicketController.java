package com.wolken.wolkenassessment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.wolkenassessment.dto.UserandTicketDTO;
import com.wolken.wolkenassessment.service.UserandTicketService;

@RestController
public class UserandTicketController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserandTicketService userandTicketService;
	
	@GetMapping("alltickets")
	List<UserandTicketDTO> alltickets(){
		List<UserandTicketDTO> userandTicketDTOs=null;
		try {
			userandTicketDTOs = userandTicketService.getAllTicket();
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return userandTicketDTOs;
	}
	
	@GetMapping("usertickets")
	List<UserandTicketDTO> usertickets(@RequestParam int customerId){
		List<UserandTicketDTO> userandTicketDTOs=null;
		try {
			userandTicketDTOs = userandTicketService.getUserTicket(customerId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return userandTicketDTOs;
	}
	
}
