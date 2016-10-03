package com.serp.service;

import java.util.List;

import com.serp.entity.ElementEntity;
import com.serp.entity.MaterialEntity;
import com.serp.entity.StockRequisitionDetailsEntity;
import com.serp.model.LimitInventory;
import com.serp.model.StockRequisition;
import com.serp.model.StockRequisitionDetails;

/**
 * The Interface StockRequisitionService.
 *
 * @author KhangNDD
 */
public interface StockRequisitionService {
	
	/**
	 * Save or update.
	 *
	 * @param sr the StockRequisition
	 * @param deletedIds list of deleted id
	 * @return true, if successful
	 */
	boolean saveOrUpdate(StockRequisition sr, List<Integer> deletedIds);

	/**
	 * Find by id.
	 *
	 * @param id the id of StockRequisition
	 * @return the StockRequisition
	 */
	StockRequisition findById(Integer id);

	/**
	 * Delete.
	 *
	 * @param id the id of StockRequisition
	 * @return true, if successful
	 */
	boolean delete(Integer id);

	/**
	 * Find all.
	 *
	 * @return StockRequisition list
	 */
	List<StockRequisition> findAll();

	/**
	 * Save or update details.
	 *
	 * @param srd the StockRequisitionDetails
	 * @return true, if successful
	 */
	boolean saveOrUpdateDetails(StockRequisitionDetails srd);

	/**
	 * Delete details.
	 *
	 * @param id the id of StockRequisitionDetails
	 * @return true, if successful
	 */
	boolean deleteDetails(Integer id);

	/**
	 * Find all details by id.
	 *
	 * @param id the id of StockRequisitionDetails
	 * @return list of StockRequisitionDetails
	 * @throws Exception 
	 */
	List<StockRequisitionDetails> findAllDetailsById(Integer id) throws Exception;

	/**
	 * Find all material entity.
	 *
	 * @return list of MaterialEntity
	 */
	List<MaterialEntity> findAllMaterialEntity();

	/**
	 * Convert entity to model.
	 *
	 * @param srde the StockRequisitionDetailsEntity
	 * @return StockRequisitionDetails
	 */
	StockRequisitionDetails convertEntityToModel(StockRequisitionDetailsEntity srde);

	/**
	 * Convert model to entity.
	 *
	 * @param srd the StockRequisitionDetails
	 * @return StockRequisitionDetailsEntity
	 */
	StockRequisitionDetailsEntity convertModelToEntity(StockRequisitionDetails srd);

	/**
	 * Find all element entity.
	 *
	 * @return list of ElementEntity
	 */
	List<ElementEntity> findAllElementEntity();

	/**
	 * Find all StockRequisitionDetailsEntity.
	 *
	 * @param id the id
	 * @return list of StockRequisitionDetailsEntity
	 */
	List<StockRequisitionDetailsEntity> findAllDetailsEntity(Integer id);

	/**
	 * Gets data from limit inventory.
	 *
	 * @param limitInventory the limit inventory
	 * @return StockRequisition with data from limitInventory
	 */
	StockRequisition getDataFromLimitInventory(LimitInventory limitInventory);
}
