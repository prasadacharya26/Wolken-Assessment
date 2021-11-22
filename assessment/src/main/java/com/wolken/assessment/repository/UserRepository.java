package com.wolken.assessment.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.assessment.entity.UserEntity;


public interface UserRepository extends JpaRepositoryImplementation<UserEntity, Integer> {
	UserEntity findUserByEmail(String email);
	UserEntity findUserByContactNumber(long contactNumber);
	UserEntity findById(int id);
}
