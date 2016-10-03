package com.serp.service;

import java.util.List;

import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;

public interface TechnologyService {
	public List<ProcessingTechnologyDetail> list();

	public void add(ProcessingTechnologyDetail technology);

	public void update(ProcessingTechnologyDetail technology);
	
	public ProcessingTechnologyDetail findById(Integer current_idPTD);
	
	public void save(ProcessingTechnologyDetail technology);

	public List<ProcessingTechnology> listProcessingTechnology();

}
