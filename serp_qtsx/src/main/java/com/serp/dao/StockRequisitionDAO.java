package com.serp.dao;

import java.util.List;

import com.serp.model.StockRequisition;
import com.serp.model.StockRequisitionDetails;

/**
 * The Interface StockRequisitionDAO.
 *
 * @author KhangNDD
 */
public interface StockRequisitionDAO {

	/**
	 * Save stock requisition to database.
	 *
	 * @param sr
	 *            the StockRequisition
	 */
	void saveStockRequisition(StockRequisition sr);

	/**
	 * Update stock requisition to database.
	 *
	 * @param sr
	 *            the StockRequisition
	 */
	void updateStockRequisition(StockRequisition sr);

	/**
	 * Gets the stock requisition by id.
	 *
	 * @param id
	 *            the id
	 * @return the stock requisition by id
	 */
	StockRequisition getStockRequisitionById(Integer id);

	/**
	 * Delete stock requisition.
	 *
	 * @param id
	 *            the id
	 */
	void deleteStockRequisition(Integer id);

	/**
	 * Gets the all stock requisitions.
	 *
	 * @return List of all stock requisitions
	 */
	List<StockRequisition> getAllStockRequisitions();

	/**
	 * Save or update stock requisition details.
	 *
	 * @param srd
	 *            the StockRequisitionDetails
	 */
	void saveOrUpdateStockRequisitionDetails(StockRequisitionDetails srd);

	/**
	 * Delete stock requisition details.
	 *
	 * @param id
	 *            the id
	 */
	void deleteStockRequisitionDetails(Integer id);

	/**
	 * Gets all details by requisition id.
	 *
	 * @param id
	 *            the id
	 * @return List of all details by requisition id
	 */
	List<StockRequisitionDetails> getAllDetailsByRequisitionId(Integer id);

	/**
	 * Gets detail by detail id.
	 *
	 * @param id
	 *            the id
	 * @return StockRequisitionDetails
	 */
	StockRequisitionDetails getDetailById(Integer id);
}
