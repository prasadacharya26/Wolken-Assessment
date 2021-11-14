package com.wolken.mobile_details.service;

import java.util.List;

import com.wolken.mobile_details.dto.MobileDTO;
import com.wolken.mobile_details.entity.MobileEntity;

public interface MobileService {

	String validateandsave(MobileDTO dto);

	List getbyBrand(String brandName);

	List getbyPrice(float price);

	MobileEntity updatePriceByModelNo(String modelNo, float price);

	MobileEntity updateAvailabilityByModelName(String modelName, Boolean availabele);


}
