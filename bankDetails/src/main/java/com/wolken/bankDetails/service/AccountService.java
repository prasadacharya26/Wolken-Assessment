package com.wolken.bankDetails.service;

import java.text.ParseException;
import java.util.List;

import com.wolken.bankDetails.dto.AccountDTO;

public interface AccountService {

	String validateAndCreateAccount(AccountDTO dto) throws ParseException;

	String validateAndGetBalanceByHolderName(String holderName);

	String validateAndDeposit(long accountNo, float amount);

	String validateAndWithdraw(long accountNo, float amount);

	List<AccountDTO> validateAndGetAccountByBranchName(String branchName);

	String validateAndCloseAccount(long accountNo);

}
