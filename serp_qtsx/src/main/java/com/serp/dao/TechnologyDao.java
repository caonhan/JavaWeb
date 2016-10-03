package com.serp.dao;

import java.util.List;

import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;

public interface TechnologyDao {
	List<ProcessingTechnologyDetail> list();
	void add(ProcessingTechnologyDetail processingTechnology);
	void upadate(ProcessingTechnologyDetail technology);
	ProcessingTechnologyDetail findById(java.lang.Integer id);	
	void save(ProcessingTechnologyDetail technology);
	List<ProcessingTechnology> listProcessingTechnology();
}
