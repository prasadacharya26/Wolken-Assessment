package com.wolken.bankDetails.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolken.bankDetails.dto.AccountDTO;
import com.wolken.bankDetails.service.AccountService;

@RestController
public class AccountController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	AccountService service;
	@PostMapping("create")
	String create(@RequestBody AccountDTO dto) {
		String msg=null;
		try {
			msg = service.validateAndCreateAccount(dto);
			logger.info(""+dto);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return msg;
	}
	
	@GetMapping("getBalance")
	String getBalance(@RequestParam String holderName) {
		String accountDTO = null;
		try {
			accountDTO = service.validateAndGetBalanceByHolderName(holderName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return accountDTO;
	}
	
	@GetMapping("getBranchName")
	List<AccountDTO> getBranchName(@RequestParam String branchName) {
		List<AccountDTO> accountDTO =null;
		try {
			accountDTO = service.validateAndGetAccountByBranchName(branchName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return accountDTO;
	}
	
	@PostMapping("deposit")
	String deposite(@RequestParam long accountNo,@Param(value="amount")float amount) {
		String msg = null;
		try {
			msg = service.validateAndDeposit(accountNo,amount);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return msg;
	}
	
	@PostMapping("withdraw")
	String withdraw(@RequestParam long accountNo,@Param(value="amount")float amount) {
		String msg = null;
		try {
			msg = service.validateAndWithdraw(accountNo,amount);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return msg;
	}
	
	@PutMapping("closeAccount")
	String closeAccount(@RequestParam long accountNo) {
		String msg = null;
		try {
			msg = service.validateAndCloseAccount(accountNo);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return msg;
	}
}
