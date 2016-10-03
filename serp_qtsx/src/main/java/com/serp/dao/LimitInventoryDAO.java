package com.serp.dao;

import java.util.List;

import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;

public interface LimitInventoryDAO {
	void saveOrUpdateLimitInventory(LimitInventory li);
	
	LimitInventory getLimitInventoryById(Integer id);
	
	void deleteLimitInventory(Integer id);
	
	List<LimitInventory> getAllLimitInventories();
	
	//Detail
	void saveOrUpdateLimitInventoryDetail(LimitInventoryDetail lid);
	
	void deleteLimitInventoryDetail(Integer id);
	
	List<LimitInventoryDetail> getAllDetailByLimitInventoryId(Integer id);
	
	//Dat lam. dung xoa thanks
	List<LimitInventory> getAllIdLimitInventories();

}
