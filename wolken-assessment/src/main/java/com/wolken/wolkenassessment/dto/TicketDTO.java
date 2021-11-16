package com.wolken.wolkenassessment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketDTO {
	private int ticketId;
	private String agentId;
	private String requestType;
	private String issue;
	private String date;
	private String productId;
	private String productName;
	private String urgency;
	private String status;
	private String assignedTo;
	private int customerId;
}
