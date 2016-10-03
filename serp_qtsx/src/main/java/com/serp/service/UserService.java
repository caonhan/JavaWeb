package com.serp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.UserDAO;
import com.serp.entity.ProcessingDocumentEntity;
import com.serp.entity.RoleEntity;
import com.serp.entity.UserEntity;
import com.serp.model.Estimate;
import com.serp.model.Material;
import com.serp.model.ProcessingDocument;
import com.serp.model.Quotation;
import com.serp.model.Role;
import com.serp.model.User;

import java.util.List;
import java.util.ArrayList;

@Service("UserService")
public class UserService {
	
	private static final Log log = LogFactory.getLog(UserService.class);
	
	@Autowired
	UserDAO userDAO;
	
	public void addUser(User user) {
		log.debug("adding user instance ");
        try {
            userDAO.addUser(user);
            log.debug("adding successful");
        } catch (RuntimeException re) {
            log.error("adding failed", re);
            throw re;
        }
    }
		
	public void deleteUser(User user) {
        log.debug("deleting user instance ");
        try {
            userDAO.delete(user);
            log.debug("deleting successful");
        } catch (RuntimeException re) {
            log.error("deleting failed", re);
            throw re;
        }
    }
	
	public void updateUser(User user) {
        log.debug("updating user instance ");
        try {
            userDAO.attachDirty(user);
            log.debug("updating successful");
        } catch (RuntimeException re) {
            log.error("updating failed", re);
            throw re;
        }
    }
	
	public List<User> getAllUser() {
        log.debug("getting all user");
        try {
            List<User> list = userDAO.getAllUser();
            log.debug("getting all successful");
            return list;
        } catch (RuntimeException re) {
            log.error("getting all failed", re);
            throw re;
        }
    }
	
	public User findByUserID(String userID) {
		// TODO Auto-generated method stub
		return this.userDAO.findById(userID);
	}
	
	public UserEntity findUserEntityById(String userID){
		UserEntity userE = new UserEntity();
		User us = userDAO.findById(userID);
		
		userE.setUserID(us.getUserId());
        userE.setName(us.getName());
        userE.setBirthDate(us.getBirthdate());
        userE.setPhoneNumber(us.getPhonenumber());
        userE.setEmail(us.getEmail());
        userE.setAddress(us.getAddress());
        userE.setDepartment(us.getDepartment());
        userE.setRole_id(us.getRole().getRoleId());
        userE.setStatus(us.getStatus());
		
		return userE;
	}

/*	
	public List<UserEntity> getAllUser(){
        List<User> list = userDAO.getAllUser();
        
        UserEntity userE;
        
        List<UserEntity> luserE = new ArrayList<UserEntity>();
        for (User us : list) {
            userE = new UserEntity();
            userE.setUserID(us.getUserId());
            userE.setName(us.getName());
            userE.setBirthDate(us.getBirthdate());
            userE.setPhoneNumber(us.getPhonenumber());
            userE.setEmail(us.getEmail());
            userE.setAddress(us.getAddress());
            userE.setDepartment(us.getDepartment());
            userE.setRole_id(us.getRole().getRoleId());
            userE.setStatus(us.getStatus());
            luserE.add(userE);
        }
        return luserE;
    }
 */   	
}
