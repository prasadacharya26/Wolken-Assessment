package com.wolken.wolkenassessment.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.wolkenassessment.entity.TicketEntity;

public interface TicketRepository extends JpaRepositoryImplementation<TicketEntity, Integer> {
	TicketEntity findById(int ticketId);

	//List<TicketEntity> findByCustomerId(int customerId);
}
