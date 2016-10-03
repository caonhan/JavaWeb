package com.serp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serp.dao.CustomerHome;
import com.serp.dao.UserLoginDao;
import com.serp.entity.CustomerEntity;
import com.serp.model.Customer;

import java.util.List;
import java.util.ArrayList;

@Service("customerService")
public class CustomerService {
	private static final Log log = LogFactory.getLog(OrderService.class);
	@Autowired
	CustomerHome cusDao;
	@Autowired
	UserLoginDao userDao;

	/**
	 * This function is used get all customer
	 * 
	 * @return List<CustomerEntity>
	 */
	public List<CustomerEntity> getAllCustomer() {
		log.info(String.format("getAllcustomer in class: %s", getClass()));
		try{
			
	
		List<Customer> listCustomer = cusDao.getAllCustomer();
		CustomerEntity customerEntity;
		List<CustomerEntity> listCusEntity = new ArrayList<CustomerEntity>();
		for (Customer cus : listCustomer) {
			customerEntity = new CustomerEntity();
			customerEntity.setCustomerId(cus.getCustomerId());
			customerEntity.setCompanyName(cus.getCompanyName());
			customerEntity.setAssignee(cus.getAssignee());
			customerEntity.setAddress(cus.getAddress());
			customerEntity.setMobilePhone(cus.getMobilePhone());

			listCusEntity.add(customerEntity);

		}
		log.debug("getAllCustomer successfully!");
		return listCusEntity;
	}catch(Exception e){
		log.error(String.format("getAllCustomer in class: %s has error: %s", getClass(), e.getMessage()));
		throw e;
	}
	}


	/**
	 * This function is used to delete a Customer in database.
	 * @param cusId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteCustomer(int id) {
		log.info(String.format("deleteOrder with param 'cusId' in class: %s", getClass()));
		Customer cus = cusDao.findById(id);
		try {
			cusDao.delete(cus);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * add customer
	 */
	/**
	 * This function is used to add new Customer into database
	 * 
	 * @param CusEnt
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addCustomer(CustomerEntity cusEnt, String userId) {
		log.info(String.format("addCustomer in class: %s", getClass()));
		try{
		Customer cus = new Customer();
		cus.setCustomerId(cusEnt.getCustomerId());
		cus.setCompanyName(cusEnt.getCompanyName());
		cus.setAddress(cusEnt.getAddress());
		cus.setAssignee(cusEnt.getAssignee());
		cus.setTelephone(cusEnt.getTelePhone());
		cus.setMobilePhone(cusEnt.getMobilePhone());
		cus.setFax(cusEnt.getFax());
		cus.setEmail(cusEnt.getEmail());
		cus.setWebsite(cusEnt.getWebsite());
		cus.setCreatedBy(userDao.findById(userId).getName());
		cus.setCreatedDate(cusEnt.getCreatedDate());
		cus.setDescription(cusEnt.getDescription());

		
			cusDao.persist(cus);
			 log.debug("add new customer into database successfully");
			return true;
		} catch (Exception e) {
			 log.debug("add new customer into database fail");

			 System.err.println("add new Customer into database fail, method addCustomer(), class CustomerService");
			return false;
		}
	}

	/**
	 * This function check if there is an customer with customerId existed in
	 * database
	 * 
	 * @param customerId
	 * @return false if not existed, true if existed
	 */
	public boolean isCustomerExistedById(int CusId) {
		if (null == cusDao.findById(CusId))
			return false;
		return true;
	}

	/**
	 * This function is used to edit Customer into database
	 * 
	 * @param customerEn
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editCustomer(CustomerEntity cusEnt, String userId) {
		 log.info(String.format("editcustomer with param 'customerEn','userId' in class: %s", getClass()));
		try {
			Customer cus = cusDao.findById(cusEnt.getCustomerId());
			cus.setCustomerId(cusEnt.getCustomerId());
			cus.setCompanyName(cusEnt.getCompanyName());
			cus.setAddress(cusEnt.getAddress());
			cus.setAssignee(cusEnt.getAssignee());
			cus.setTelephone(cusEnt.getTelePhone());
			cus.setMobilePhone(cusEnt.getMobilePhone());
			cus.setFax(cusEnt.getFax());
			cus.setEmail(cusEnt.getEmail());
			cus.setWebsite(cusEnt.getWebsite());
			cus.setModifiedBy(userDao.findById(userId).getName());
			cus.setDescription(cusEnt.getDescription());
			cusDao.attachDirty(cus);
			log.debug("editCustomer successfully");
			
			return true;
		} catch (Exception e) {
			 log.error(String.format("editcustomer with param 'customerEn','userId' in class: %s has error: %s", getClass(), e.getMessage()));
			 System.err.println(String.format("editcustomer with param 'customerEn','userId' in class: %s has error: %s", getClass(),
			 e.getMessage()));
			return false;
		}
	}

	
	 /** This function find a CustomerEntity by id.
	 * @param CustomerId
	 * @return a Customer
	 */
	public CustomerEntity findCustomerEntityById(int cusId) {
		 log.info(String.format("findcustomerEntityById with param 'customerId' in class: %s", getClass()));
		try {
			CustomerEntity cusEnt = new CustomerEntity();
			Customer cus = cusDao.findById(cusId);

			// field for NV
			cusEnt.setCustomerId(cus.getCustomerId());
			cusEnt.setCompanyName(cus.getCompanyName());
			cusEnt.setAddress(cus.getAddress());
			cusEnt.setAssignee(cus.getAssignee());
			cusEnt.setTelePhone(cus.getTelephone());
			cusEnt.setMobilePhone(cus.getMobilePhone());
			cusEnt.setFax(cus.getFax());
			cusEnt.setEmail(cus.getEmail());
			cusEnt.setWebsite(cus.getWebsite());
			cusEnt.setDescription(cus.getDescription());
			cusEnt.setCreatedBy(cus.getCreatedBy());
			cusEnt.setCreatedDate(cus.getCreatedDate());
			cusEnt.setModified_by(cus.getModifiedBy());

		 log.debug("findcustomerEntityById successfully");
			return cusEnt;
		} catch (Exception e) {
			log.error(String.format("findCustomerEntityById with param 'customerId' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

}