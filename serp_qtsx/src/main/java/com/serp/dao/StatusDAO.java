package com.serp.dao;

import java.util.List;

import com.serp.model.Status;

public interface StatusDAO {

	List<Status> list();

	Status findById(Integer id);

}
