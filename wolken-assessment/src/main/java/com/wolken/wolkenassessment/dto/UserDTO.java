package com.wolken.wolkenassessment.dto;

import java.util.List;

import com.wolken.wolkenassessment.entity.TicketEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
	private int id;
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
	private List<TicketEntity> ticket;
}
