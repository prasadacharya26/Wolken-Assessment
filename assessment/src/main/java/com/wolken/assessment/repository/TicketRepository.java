package com.wolken.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.assessment.entity.TicketEntity;

public interface TicketRepository extends JpaRepositoryImplementation<TicketEntity, Integer> {
	TicketEntity findById(int ticketId);
	
	List<TicketEntity> findByCustomerId(int customerId);
}
