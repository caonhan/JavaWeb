package com.vn.gcs.serp.dao;

import java.util.List;

import com.vn.gcs.serp.model.Users;

public interface UserDao {
	List<Users> findAllUsers();
}
