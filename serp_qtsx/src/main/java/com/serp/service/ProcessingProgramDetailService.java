package com.serp.service;

import java.util.List;

import com.serp.model.ProcessingProgramDetail;

/**
 * The interface ProcessingProgramService.
 *
 * @author ThoNP
 */
public interface ProcessingProgramDetailService {

	/**
	 * Get the all processing programs.
	 *
	 * @return List of all processing programs.
	 */
	public List<ProcessingProgramDetail> listProcessingProgramDetail(Integer id);

	/**
	 * Save or update processing program to database.
	 *
	 * @param processing
	 *            program the ProcessingProgram
	 */
	public boolean add(ProcessingProgramDetail processingProgramDetail);

	/**
	 * Gets processing program by id.
	 *
	 * @param id
	 *            ProcessingProgram->id
	 * @return ProcessingProgram.
	 */
	public ProcessingProgramDetail searchProcessingProgramDetail(Integer id);

	/**
	 * Delete processing program.
	 *
	 * @param id
	 *            ProcessingProgram->id
	 */
	public boolean delete(ProcessingProgramDetail processingProgramDetail);
}
