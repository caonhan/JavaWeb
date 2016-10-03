package com.serp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.ProductionOrder;

@Repository("productionOrderHome")
@Transactional
public class ProductionOrderHomeImpl implements ProductionOrderHome {
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	private static final Log log = LogFactory.getLog(ProductionOrderHomeImpl.class);

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdateProductionOrder(ProductionOrder po) {
		log.debug("Saving or Updating ProductionOrder instance with id: " + po.getPoId());

		try {
			getSession().saveOrUpdate(po);
			log.debug("Save or update successful");
		} catch (RuntimeException po1) {
			log.error("Save or Update failed", po1);
			throw po1;
		}
	}

	@Override
	public ProductionOrder getProductionOrderById(int id) {
		log.debug("getting ProductionOrder instance with id: " + id);

		try {
			ProductionOrder instance = (ProductionOrder) getSession().get(ProductionOrder.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public void deleteProductionOrder(Integer id) {
		log.debug("deleting ProductionOrder instance");

		try {
			ProductionOrder sRequisition = (ProductionOrder) getSession().load(ProductionOrder.class, id);
			getSession().delete(sRequisition);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductionOrder> getAllProductionOrders() {
		log.debug("finding all Production Order instance");

		try {
			List<ProductionOrder> srList = new ArrayList<ProductionOrder>();

			// Select distinct from ProductionOrder table (avoid duplicate)
			srList = getSession().createQuery("FROM " + ProductionOrder.class.getName())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

			log.debug("find all successful, result size: " + srList.size());
			return srList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	


}
