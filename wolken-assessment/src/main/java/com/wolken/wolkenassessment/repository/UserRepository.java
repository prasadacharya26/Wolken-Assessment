package com.wolken.wolkenassessment.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.wolkenassessment.entity.UserEntity;

public interface UserRepository extends JpaRepositoryImplementation<UserEntity, Integer> {
	UserEntity findUserByEmail(String email);
	UserEntity findUserByContactNumber(long contactNumber);
	UserEntity findById(int id);
}
