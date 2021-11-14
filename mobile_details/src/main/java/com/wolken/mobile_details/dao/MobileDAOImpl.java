package com.wolken.mobile_details.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wolken.mobile_details.entity.MobileEntity;


@Component
public class MobileDAOImpl implements MobileDAO {
	@Autowired
	SessionFactory factory;
	Logger logger=Logger.getLogger(MobileDAOImpl.class);
	public String save(MobileEntity entity) {
		Session session=null;
		String result=null;
		try {
			session=factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.save(entity);
			transaction.commit();
			result="Data Saved";
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			session.close();
		}
		return result;
		
	}
	
	public List getByBrandName(String brandName) {
		Session session=null;
		List<MobileEntity> list=null;
		try {
			session=factory.openSession();
			Query<MobileEntity> query=session.createNamedQuery("getByBrandName");
			query.setParameter("name", brandName);
			list=query.list();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			session.close();
		}	
		return list;
	}

	public List getByPrice(float price) {
		Session session=null;
		List<MobileEntity> list=null;
		try {
			session=factory.openSession();
			Query<MobileEntity> query=session.createNamedQuery("getByPrice");
			query.setParameter("price", price);
			list=query.list();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			session.close();
		}	
		return list;
	}

	
	public MobileEntity updatePriceByModelNo(String modelNo, float price) {
		Session session=null;
		int row;
		MobileEntity entity = new MobileEntity();
		try {
			session=factory.openSession();
			Transaction transaction=session.beginTransaction();
			Query<MobileEntity> query=session.createNamedQuery("updatePriceByModelNo");
			query.setParameter("price", price);
			query.setParameter("modelNo", modelNo);
			row=query.executeUpdate();
			transaction.commit();
			if(row>0) {
				entity.setModelNo(modelNo);
				entity.setPrice(price);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			session.close();
		}	
		return entity;
	}

	public MobileEntity updateAvailabilityByModelName(String modelName, Boolean availabele) {
		Session session=null;
		int row;
		MobileEntity entity = new MobileEntity();
		try {
			session=factory.openSession();
			Transaction transaction=session.beginTransaction();
			Query<MobileEntity> query=session.createNamedQuery("updateAvailabilityByModelName");
			query.setParameter("availabele", availabele);
			query.setParameter("modelName", modelName);
			row=query.executeUpdate();
			transaction.commit();
			if(row>0) {
				entity.setModelName(modelName);;
				entity.setAvailabele(availabele);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			session.close();
		}	
		return entity;
	}

}
