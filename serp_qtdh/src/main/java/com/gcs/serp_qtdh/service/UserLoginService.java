package com.gcs.serp_qtdh.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.serp_qtdh.dao.UserLoginDao;
import com.gcs.serp_qtdh.model.Users;

/**
 * 
 * @author PhiTT
 *
 */
@Service("UsersLoginService")
@Transactional
public class UserLoginService {
	
	@Autowired
	UserLoginDao dao;
	
	private static final Log log = LogFactory.getLog(UserLoginService.class);
	
	/**
	 * This function is used to get a Users from database by it's id
	 * @param id
	 * @return Users
	 */
	public Users findById(String id){
		log.info(String.format("findById with param 'id' in class: %s", getClass()));
		try{
			return dao.findById(id);
		}catch(Exception e){
			log.error(String.format("findById with param 'id' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
