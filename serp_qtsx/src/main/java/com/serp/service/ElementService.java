package com.serp.service;

import java.util.List;

import com.serp.model.Element;

public interface ElementService {
	List<Element> findAll();

	Element findById(String id);

	void cleanTrash();
}
