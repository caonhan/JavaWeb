package com.gcs.serp_qtdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.serp_qtdh.dao.UserDao;
import com.gcs.serp_qtdh.model.Users;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao dao;
	
	public List<Users> findAllUsers() {
        return dao.findAllUsers();
    }
}
