package com.wolken.wolkenassessment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
public class UserEntity {
	@Id
	@GenericGenerator(name="userId", strategy = "increment")
	@GeneratedValue(generator = "userId")
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
	@OneToMany(targetEntity = TicketEntity.class,
		    cascade = CascadeType.ALL)
	@JoinColumn(name="customerId",referencedColumnName = "id")
	private List<TicketEntity> ticket;
	
}
