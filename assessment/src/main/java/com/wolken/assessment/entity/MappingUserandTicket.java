package com.wolken.assessment.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class MappingUserandTicket {

	@Id
	@GenericGenerator(name="mapId", strategy = "increment")
	@GeneratedValue(generator = "mapId")
	private int m_id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ticketId",referencedColumnName = "ticketId")
	private TicketEntity ticketEntity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customerId",referencedColumnName = "id")
	private UserEntity userEntity;
	
}
