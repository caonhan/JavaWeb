package com.serp.dao;

import java.util.List;

import com.serp.model.ProcessingProgramDetail;


/**
 * The interface ProcessingProgramDetailDAO.
 *
 * @author ThoNP
 */
public interface ProcessingProgramDetailDAO {

	/**
	 * Get the all processing programs detail.
	 *
	 * @param id
	 *            ProcessingProgram->id
	 * @return List of all processing programs detail from processing program.
	 */
	public List<ProcessingProgramDetail> listProcessingProgramDetail(Integer id);

	/**
	 * Save or update processing program detail to database.
	 *
	 * @param processing
	 *            program detail the ProcessingProgramDetail
	 */
	public boolean add(ProcessingProgramDetail processingProgramDetail);

	/**
	 * Gets processing program detail by id.
	 *
	 * @param id
	 *            ProcessingProgramDetail->id
	 * @return ProcessingProgramDetail.
	 */
	public ProcessingProgramDetail searchProcessingProgramDetail(
			java.lang.Integer id);

	/**
	 * Delete processing program detail.
	 *
	 * @param id
	 *            ProcessingProgramDetail->id
	 */
	public boolean delete(ProcessingProgramDetail processingProgramDetail);
}
