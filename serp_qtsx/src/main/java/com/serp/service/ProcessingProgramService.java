package com.serp.service;

import java.util.List;

import com.serp.model.ProcessingProgram;

/**
 * The interface ProcessingProgramService.
 *
 * @author ThoNP
 */
public interface ProcessingProgramService {

	/**
	 * Get the all processing programs.
	 *
	 * @return List of all processing programs.
	 */
	public List<ProcessingProgram> listProcessingProgram();

	/**
	 * Save or update processing program to database.
	 *
	 * @param processing
	 *            program the ProcessingProgram
	 */
	public boolean add(ProcessingProgram processingProgram);

	/**
	 * Gets processing program by id.
	 *
	 * @param id
	 *            ProcessingProgram->id
	 * @return ProcessingProgram.
	 */
	public ProcessingProgram findProcessingProgramById(Integer id);

	/**
	 * Delete processing program.
	 *
	 * @param id
	 *            ProcessingProgram->id
	 */
	public boolean delete(ProcessingProgram processingProgram);
}
