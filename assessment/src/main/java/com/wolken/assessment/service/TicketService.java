package com.wolken.assessment.service;

import java.text.ParseException;
import java.util.List;

import com.wolken.assessment.dto.TicketDTO;

public interface TicketService {

	String validateAndAddTicket(TicketDTO ticketDTO) throws ParseException;

	List<TicketDTO> validateAndGetAll();

	String updateTicketById(TicketDTO ticketDTO) throws ParseException;

	String updateStatusById(int ticketId, String status);

	List<TicketDTO> findMyTickets(int customerId);

	TicketDTO checkProgress(int tickedId);

	String validateAndSaveAll(List<TicketDTO> ticketDTO) throws ParseException;


}
