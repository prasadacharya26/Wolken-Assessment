package com.wolken.bankDetails.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountDTO {
	private long accountNo;
	private String holderName;
	private long contactNumber;
	private String email;
	private String address;
	private String branchName;
	private String typeOfAccount;
	private float amount;
	private boolean status;
	private String nominee;
	private String gender;
	private String dob;
}
