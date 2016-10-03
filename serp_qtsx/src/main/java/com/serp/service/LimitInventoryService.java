package com.serp.service;

import java.util.List;

import com.serp.entity.ElementEntity;
import com.serp.entity.LimitInventoryDetailEntity;
import com.serp.entity.MaterialEntity;
import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;

public interface LimitInventoryService {
	boolean saveOrUpdate(LimitInventory li);  // ??boolean
	
	LimitInventory findById(Integer id);
	
	boolean delete(Integer id);
	
	List<LimitInventory> findAll();
	
	boolean saveOrUpdateDetail(LimitInventoryDetail lid);
	
	boolean deleteDetail(Integer id);
	
	List<LimitInventoryDetail> findAllDetailById(Integer id);
	
	List<LimitInventoryDetailEntity> findAllDetailEntity(Integer id);
	
	List<MaterialEntity> findAllMaterialEntity();
	
	List<ElementEntity> findAllElementEntity();

	LimitInventoryDetail convertEntityToModel(LimitInventoryDetailEntity lide);
	
	LimitInventoryDetailEntity convertModelToEntity(LimitInventoryDetail lid);
}
