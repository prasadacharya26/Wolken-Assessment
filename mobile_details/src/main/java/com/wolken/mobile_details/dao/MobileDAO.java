package com.wolken.mobile_details.dao;

import java.util.List;

import com.wolken.mobile_details.entity.MobileEntity;

public interface MobileDAO {

	String save(MobileEntity entity);

	List getByBrandName(String brandName);

	List getByPrice(float price);

	MobileEntity updatePriceByModelNo(String modelNo, float price);

	MobileEntity updateAvailabilityByModelName(String modelName, Boolean availabele);


}
