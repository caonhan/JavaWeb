package com.gcs.serp_qtdh.dao;

import java.util.List;

import com.gcs.serp_qtdh.model.Users;

public interface UserDao {
	List<Users> findAllUsers();
}
