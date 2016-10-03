package com.serp.dao;

import java.util.List;

import com.serp.model.ProductionOrder;
import com.serp.model.StockRequisition;
/**
 * Home object for domain model class ProductionOrder.
 * @see com.serp.dao.ProductionOrder
 * @author Hibernate Tools
 */

public interface ProductionOrderHome {

	void saveOrUpdateProductionOrder(ProductionOrder sr);

	ProductionOrder getProductionOrderById(int id);

	void deleteProductionOrder(Integer id);
	
	List<ProductionOrder> getAllProductionOrders();

	
}
