package com.serp.dao;

import java.util.List;

/**
 * The interface ProcessingProgramDAO.
 *
 * @author ThoNP
 */
import com.serp.model.ProcessingProgram;

/**
 * The interface ProcessingProgramDAO.
 *
 * @author ThoNP
 */
public interface ProcessingProgramDAO {

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
