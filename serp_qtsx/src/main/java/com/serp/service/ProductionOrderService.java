package com.serp.service;

import java.util.List;

import com.serp.model.ProductionOrder;


public interface ProductionOrderService {
	
	boolean saveOrUpdate(ProductionOrder po);

	ProductionOrder findById(int id);

	boolean delete(Integer id);

	List<ProductionOrder> findAll();

}
