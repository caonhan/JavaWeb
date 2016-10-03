package com.serp.dao;

import java.util.List;

import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;

public interface ProcessingTechnologyDAO {
	public List<ProcessingTechnology> listProcessingEditor_leader(String status);

	public List<ProcessingTechnology> listProcessingEditor_manager(String status);

	public List<ProcessingTechnologyDetail> listProcessingTechnologies(int id);

	public void checkProcessTechnology(ProcessingTechnology processingTechnology);

	public void approvalTechnology(ProcessingTechnology processingTechnology);

	public ProcessingTechnology findById(Integer id);

	public ProcessingProgram findProgram(String element_id);

	public List<ProcessingProgramDetail> findProgramDetail(Integer program_id);
	
}
