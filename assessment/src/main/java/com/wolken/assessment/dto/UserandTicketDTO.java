package com.wolken.assessment.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserandTicketDTO {
	private int ticketId;
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private long contactNumber;
	private String gender;
	private String address;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String agentId;
	private String requestType;
	private String issue;
	private Date date;
	private String urgency;
	private String status;
	private String assignedTo;
}
