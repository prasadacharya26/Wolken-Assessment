package com.wolken.bankDetails.entity;

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
@NoArgsConstructor
@Table
@ToString
public class EmployeeEntity {
	@Id
	@GenericGenerator(name="empid",strategy = "increment")
	@GeneratedValue(generator = "empid")
	private int id;
	private String name;
	private long contactNumber;
	private String designation;
	private String email;
	private float salary;
}
