package com.wolken.bankDetails.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.TypeConstraintException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.bankDetails.dto.AccountDTO;
import com.wolken.bankDetails.entity.AccountEntity;
import com.wolken.bankDetails.repository.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	AccountRepository accountRepository;
	SimpleDateFormat dob=new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public String validateAndCreateAccount(AccountDTO dto) throws ParseException {
	
		try {
			if(dto!=null) {
				if(dto.getAccountNo()>0) {
					AccountEntity accEntity = accountRepository.findByAccountNo(dto.getAccountNo());
					if(accEntity==null) {
						if(!dto.getHolderName().equals(null) && !dto.getHolderName().equals("")) {
							if(!dto.getEmail().equals(null) && !dto.getEmail().equals("")) {
								if(dto.getContactNumber()>5999999999l && dto.getContactNumber()<9999999999l) {
									if(!dto.getAddress().equals(null) && !dto.getAddress().equals("")) {
										if(!dto.getBranchName().equals(null) && !dto.getBranchName().equals("")) {
											if(!dto.getTypeOfAccount().equals(null) && !dto.getTypeOfAccount().equals("")) {
												if(dto.getAmount()>999) {
														if(!dto.getNominee().equals(null) && !dto.getNominee().equals("")) {
															if(!dto.getGender().equals(null) && !dto.getGender().equals("")) {
																if(!dto.getDob().equals(null) && !dto.getDob().equals("")) {
																	AccountEntity accountEntity = new AccountEntity();
																	logger.info(""+dto);
																	Date dateofbirth = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDob());
																	accountEntity.setDob(dateofbirth);
																	BeanUtils.copyProperties(dto, accountEntity);
																	accountRepository.save(accountEntity);
																	logger.info(""+accountEntity);
																	logger.info("Account created");
																	return "Account created";
																}else{
																	logger.info("Invalid date of birth");
																	return "Invalid date of birth";
																}
															}else{
																logger.info("Invalid gender");
																return "Invalid gender";
															}
														}else{
															logger.info("Invalid nominee");
															return "Invalid nominee";
														}
												}else{
													logger.info("Invalid amount");
													return "Invalid amount";
												}
											}else{
												logger.info("Invalid type of account");
												return "Invalid type of account";
											}
										}else{
											logger.info("Invalid account branch name");
											return "Invalid account branch name";
										}
									}else{
										logger.info("Invalid account holder address");
										return "Invalid account holder address";
									}
								}else {
									logger.info("Invalid account holder contact number");
									return "Invalid account holder contact number";
								}
							}else {
								logger.info("Invalid account holder email");
								return "Invalid account holder email";
							}
						}else {
							logger.info("Invalid account holder name");
							return "Invalid account holder name";
						}
					}else {
						logger.info("Account number already exists");
						return "Account number already exists";
					}
					
				}else {
					logger.info("Invalid account number");
					return "Invalid account number";
				}
			}else {
				logger.info("Object null");
				return "Object Null";
			}
		} catch (NullPointerException | TypeConstraintException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return null;
	}
	
	@Override
	public String validateAndGetBalanceByHolderName(String holderName) {
		AccountDTO accountDTO = new AccountDTO();
		try {
			if(!holderName.equals(null) && !holderName.equals("")) {
				AccountEntity accountEntity = accountRepository.findBalanceByHolderName(holderName);
				BeanUtils.copyProperties(accountEntity,accountDTO);
				accountDTO.setDob(dob.format(accountEntity.getDob()));
				logger.info(""+accountDTO);
				return "Current balance:"+accountDTO.getAmount();
			}else {
				logger.info("Invalid account holder name");
				return "Invalid account holder name";
			}
		} catch (NullPointerException | TypeConstraintException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return null;
		
	}

	@Override
	public List<AccountDTO> validateAndGetAccountByBranchName(String branchName) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		try {
			List<AccountEntity> accountEntities = accountRepository.findAccountByBranchName(branchName);
			for (AccountEntity accountEntity : accountEntities) {
				AccountDTO accountDTO =new AccountDTO();
				BeanUtils.copyProperties(accountEntity,accountDTO);
				accountDTO.setDob(dob.format(accountEntity.getDob()));
				accountDTOs.add(accountDTO);	
			}
		} catch (NullPointerException | TypeConstraintException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		logger.info(""+accountDTOs);
		return accountDTOs;
	}

	@Override
	public String validateAndDeposit(long accountNo, float amount) {
		try {
			if(!Objects.isNull(accountNo)) {
				AccountEntity accountEntity = accountRepository.findByAccountNo(accountNo);
				if(accountEntity!=null) {
					if(amount > 0 ) {
						float currentBlance = accountEntity.getAmount() + amount;
						logger.info("balnce:"+currentBlance);
						accountEntity.setAmount(currentBlance);
						accountRepository.save(accountEntity);
						logger.info(""+accountEntity.getAmount());
						return "Credited:"+amount+"\n"+"balance:"+accountEntity.getAmount();
					}else {
						logger.info("invalid amount");
						return "Invalid amount";
					}
				}else {
					logger.info("Not found");
					return "Not found";
				}	
			}else {
				logger.info("Invalid Account number");
				return "Invalid Account number";
			}	
		} catch (NullPointerException | TypeConstraintException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return null;
	}

	@Override
	public String validateAndWithdraw(long accountNo, float amount) {
		try {
			if(!Objects.isNull(accountNo)) {
				AccountEntity accountEntity = accountRepository.findByAccountNo(accountNo);
				if(accountEntity!=null) {
					if(amount > 0 ) {
						if(accountEntity.getAmount() > amount) {
							float currentBlance = accountEntity.getAmount() - amount;
							logger.info("balnce:"+currentBlance);
							accountEntity.setAmount(currentBlance);
							accountRepository.save(accountEntity);
							logger.info(""+accountEntity.getAmount());
							return "Credited:"+amount+"\n"+"balance:"+accountEntity.getAmount();
						}else {
							logger.info("Insufficient balance");
							return "Insufficient balance";
						}
					}else {
						logger.info("invalid amount");
						return "Invalid amount";
					}
				}else {
					logger.info("Not found");
					return "Not found";
				}	
			}else {
				logger.info("Invalid Account number");
				return "Invalid Account number";
			}	
		} catch (NullPointerException | TypeConstraintException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return null;
	}

	@Override
	public String validateAndCloseAccount(long accountNo) {
		try {
			if(!Objects.isNull(accountNo)) {
				AccountEntity accountEntity = accountRepository.findByAccountNo(accountNo);
				if(accountEntity!=null) {
					logger.info(""+accountEntity);
					accountEntity.setStatus(false);
					accountRepository.save(accountEntity);
					logger.info(""+accountEntity);
					return "Account closed";
				}else {
					logger.info("Account not found");
					return "Account not found";
				}
			}else {
				logger.info("invalid account number");
				return "Invalid account number";
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
		}
		return "Account not found";
	}

}
