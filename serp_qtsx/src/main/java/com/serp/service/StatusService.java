package com.serp.service;

import java.util.List;

import com.serp.model.Status;

public interface StatusService {
	List<Status> list();

	Status findById(int id);

	Status findByName(String name);
}
