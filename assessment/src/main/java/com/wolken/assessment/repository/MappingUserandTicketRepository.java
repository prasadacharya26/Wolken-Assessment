package com.wolken.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.assessment.entity.MappingUserandTicket;
import com.wolken.assessment.entity.UserEntity;
import com.wolken.assessment.entity.UserandTicketEntity;



public interface MappingUserandTicketRepository extends JpaRepositoryImplementation<MappingUserandTicket, Integer>{

	void save(UserandTicketEntity userandTicketEntity);
//	UserandTicketEntity findById(int ticketId);
//	List<MappingUserandTicket> findBycustomerId(int customerId);

	List<UserEntity> findByUserEntity(MappingUserandTicket mappingUserandTicket);

}
