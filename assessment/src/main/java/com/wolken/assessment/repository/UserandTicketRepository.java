package com.wolken.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.assessment.entity.UserandTicketEntity;


public interface UserandTicketRepository extends JpaRepositoryImplementation<UserandTicketEntity, Integer> {
	
	UserandTicketEntity findById(int ticketId);
	List<UserandTicketEntity> findBycustomerId(int customerId);

}
