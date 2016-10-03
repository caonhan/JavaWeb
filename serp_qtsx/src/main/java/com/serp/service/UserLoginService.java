package com.serp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serp.dao.UserLoginDao;
import com.serp.model.User;

/**
 * 
 * @author PhiTT
 *
 */
@Service("UserLoginService")
@Transactional
public class UserLoginService {
	
	@Autowired
	UserLoginDao dao;
	
	private static final Log log = LogFactory.getLog(UserLoginService.class);
	
	/**
	 * This function is used to get a User from database by it's id
	 * @param id
	 * @return User
	 */
	public User findById(String id){
		log.info(String.format("findById with param 'id' in class: %s", getClass()));
		try{
			return dao.findById(id);
		}catch(Exception e){
			log.error(String.format("findById with param 'id' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
