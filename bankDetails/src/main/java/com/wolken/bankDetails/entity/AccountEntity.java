package com.wolken.bankDetails.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@ToString
public class AccountEntity {
	@Id
	@GenericGenerator(name="accountId",strategy = "increment")
	@GeneratedValue(generator = "accountId")
	private int id;
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
	private Date dob;
}
