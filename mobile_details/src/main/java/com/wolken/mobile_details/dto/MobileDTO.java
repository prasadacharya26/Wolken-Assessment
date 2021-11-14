package com.wolken.mobile_details.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MobileDTO {
	private String brandName;
	private String modelNo;
	private String modelName;
	private String type;
	private byte ram;
	private short rom;
	private float price;
	private Boolean availabele;
}
