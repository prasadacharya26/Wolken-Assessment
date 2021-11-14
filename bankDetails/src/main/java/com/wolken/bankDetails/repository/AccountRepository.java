package com.wolken.bankDetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.bankDetails.entity.AccountEntity;

public interface AccountRepository extends JpaRepositoryImplementation<AccountEntity, Integer> {
	AccountEntity findBalanceByHolderName(String holderName);
	List<AccountEntity> findAccountByBranchName(String branchName);
	AccountEntity findByAccountNo(long accountNo);
}
