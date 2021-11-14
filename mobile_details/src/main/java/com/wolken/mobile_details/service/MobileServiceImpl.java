package com.wolken.mobile_details.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wolken.mobile_details.dao.MobileDAO;
import com.wolken.mobile_details.dto.MobileDTO;
import com.wolken.mobile_details.entity.MobileEntity;
@Component
public class MobileServiceImpl implements MobileService {
	@Autowired
	MobileDAO dao;
	Logger logger=Logger.getLogger(MobileServiceImpl.class);
	public String validateandsave(MobileDTO dto) {
		if(!dto.getBrandName().equals(null)&& !dto.getBrandName().equals("")) {
			if(!dto.getModelNo().equals(null) && !dto.getModelNo().equals("")) {
				if(!dto.getModelName().equals(null) && !dto.getModelName().equals("")) {
					if(!dto.getType().equals(null) && !dto.getType().equals("")) {
						if(dto.getRam()>3) {
							if(dto.getRom()>=8) {
								if(dto.getPrice()>1000) {
									if(dto.getAvailabele()!=null) {
										MobileEntity entity=new MobileEntity();
										BeanUtils.copyProperties(dto, entity);
										logger.info(entity);
										String rows=dao.save(entity);
										if(!rows.equals(null)) {
											return "Data Saved";
										}else {
											return "Data not saved";
										}
									}else {
										logger.error("Invalid Availablity");
										return "Invalid availablity";
									}
								}else {
									logger.error("invalid Price");
									return "Invalid price";
								}
							}else {
								logger.error("Invalid ROM");
								return "Invalid ROM";
							}
						}else {
							logger.error("Invalid RAM");
							return "Invalid RAM";
						}
					}else {
						logger.error("Invalid Type");
						return "Invalid Type";
					}
				}else {
					logger.error("Invalid Model name");
					return "Invalid model name";
				}
			}else {
				logger.error("Invalid Model number");
				return "Invalid model number";
			}
		}else {
			logger.error("Invalid Brand name");
			return "Invalid barnd name";
		}
	}
	
	public List getbyBrand(String brandName) {
		List list = null;
		if(!brandName.equals(null) && !brandName.equals("")) {
			MobileEntity entity=new MobileEntity();
			list=dao.getByBrandName(brandName);
		}
		return list;
	}

	public List getbyPrice(float price) {
		List list = null;
		if(price>1000) {
			MobileEntity entity=new MobileEntity();
			list=dao.getByPrice(price);
		}
		return list;
	}

	public MobileEntity updatePriceByModelNo(String modelNo, float price) {
		MobileEntity entity=new MobileEntity();
		if(!modelNo.equals(null) && !modelNo.equals("")) {
			if(price>1000) {
				entity=dao.updatePriceByModelNo(modelNo,price);
			}
		}
		return entity;
	}

	public MobileEntity updateAvailabilityByModelName(String modelName, Boolean availabele) {
		MobileEntity entity=new MobileEntity();
		if(!modelName.equals(null) && !modelName.equals("")) {
			if(availabele!=null) { 
				entity=dao.updateAvailabilityByModelName(modelName,availabele);
			}
		}
		return entity;
	}
}
