package com.wolken.assessment.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@ToString
public class UserandTicketEntity{
	@Id
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
