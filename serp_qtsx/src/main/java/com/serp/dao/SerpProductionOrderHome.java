package com.serp.dao;

// Generated Apr 12, 2016 11:39:18 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.serp.model.ProductionOrder;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ProductionOrder.
 * @see com.serp.dao.ProductionOrder
 * @author Hibernate Tools
 */
public class SerpProductionOrderHome {

	private static final Log log = LogFactory
			.getLog(SerpProductionOrderHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(ProductionOrder transientInstance) {
		log.debug("persisting ProductionOrder instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductionOrder instance) {
		log.debug("attaching dirty ProductionOrder instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProductionOrder instance) {
		log.debug("attaching clean ProductionOrder instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProductionOrder persistentInstance) {
		log.debug("deleting ProductionOrder instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProductionOrder merge(ProductionOrder detachedInstance) {
		log.debug("merging ProductionOrder instance");
		try {
			ProductionOrder result = (ProductionOrder) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductionOrder findById(java.lang.Integer id) {
		log.debug("getting ProductionOrder instance with id: " + id);
		try {
			ProductionOrder instance = (ProductionOrder) sessionFactory
					.getCurrentSession().get(
							"com.serp.dao.SerpProductionOrder", id);
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

	public List<ProductionOrder> findByExample(ProductionOrder instance) {
		log.debug("finding ProductionOrder instance by example");
		try {
			List<ProductionOrder> results = (List<ProductionOrder>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.dao.SerpProductionOrder")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
