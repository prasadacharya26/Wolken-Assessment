package com.wolken.wolkenassessment.service;

import java.text.ParseException;
import java.util.List;

import com.wolken.wolkenassessment.dto.TicketDTO;

public interface TicketService {

	String validateAndAddTicket(TicketDTO ticketDTO) throws ParseException;

	List<TicketDTO> validateAndGetAll();

	String updateTicketById(TicketDTO ticketDTO) throws ParseException;

}
