package com.wolken.bankDetails.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeDTO {
	private String name;
	private long contactNumber;
	private String designation;
	private String email;
	private float salary;
}
