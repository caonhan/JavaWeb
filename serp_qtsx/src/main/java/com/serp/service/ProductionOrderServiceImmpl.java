package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ProductionOrderHome;
import com.serp.model.ProductionOrder;

@Service("productionOrderService")
public class ProductionOrderServiceImmpl implements ProductionOrderService{
	
	@Autowired
	protected ProductionOrderHome productionOrderHome;
	
	private static final Log log = LogFactory.getLog(ProductionOrderServiceImmpl.class);

	public void setProductionOrderHome(ProductionOrderHome productionOrderHome) {
		this.productionOrderHome = productionOrderHome;
	}
	
	
	@Override
	public boolean saveOrUpdate(ProductionOrder po) {
		// TODO Auto-generated method stub
		log.info("saveOrUpdate function");
		try {
			productionOrderHome.saveOrUpdateProductionOrder(po);
			return true;
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute: " + po + " - Error message: " + ne.getMessage());
			System.err.println("Production Order Save or Update failed");
			return false;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("Production Order Save or Update failed");
			return false;
		}
	}

	@Override
	public ProductionOrder findById(int id) {
		// TODO Auto-generated method stub
		log.info("findById function");
		try {
			return productionOrderHome.getProductionOrderById(id);
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		log.info("delete function");
		try {
			productionOrderHome.deleteProductionOrder(id);
			log.info("Production Order delete successful");
			return true;
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
			System.err.println("Production Order delete failed");
			return false;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("Production Order delete failed");
			return false;
		}
	}

	@Override
	public List<ProductionOrder> findAll() {
		// TODO Auto-generated method stub
		log.info("findAll function");
		try {
			return productionOrderHome.getAllProductionOrders();
		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("findAll failed");
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("findAll failed");
		}
		return null;
	}

}
