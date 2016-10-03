package com.serp.dao;

import java.util.List;

import com.serp.model.Element;

public interface ElementDAO {
	void persist(Element transientInstance);

	void attachDirty(Element instance);

	void attachClean(Element instance);

	void delete(Element persistentInstance);

	Element merge(Element detachedInstance);

	Element findById(java.lang.String id);

	List<Element> findAll();
}
