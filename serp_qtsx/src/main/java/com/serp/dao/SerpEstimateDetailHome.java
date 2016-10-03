package com.serp.dao;

// Generated Apr 12, 2016 11:39:18 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.serp.model.EstimateDetail;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class EstimateDetail.
 * @see com.serp.dao.EstimateDetail
 * @author Hibernate Tools
 */
public class SerpEstimateDetailHome {

	private static final Log log = LogFactory
			.getLog(SerpEstimateDetailHome.class);

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

	public void persist(EstimateDetail transientInstance) {
		log.debug("persisting EstimateDetail instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(EstimateDetail instance) {
		log.debug("attaching dirty EstimateDetail instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EstimateDetail instance) {
		log.debug("attaching clean EstimateDetail instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(EstimateDetail persistentInstance) {
		log.debug("deleting EstimateDetail instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EstimateDetail merge(EstimateDetail detachedInstance) {
		log.debug("merging EstimateDetail instance");
		try {
			EstimateDetail result = (EstimateDetail) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EstimateDetail findById(java.lang.Integer id) {
		log.debug("getting EstimateDetail instance with id: " + id);
		try {
			EstimateDetail instance = (EstimateDetail) sessionFactory
					.getCurrentSession().get("com.serp.dao.SerpEstimateDetail",
							id);
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

	public List<EstimateDetail> findByExample(EstimateDetail instance) {
		log.debug("finding EstimateDetail instance by example");
		try {
			List<EstimateDetail> results = (List<EstimateDetail>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.dao.SerpEstimateDetail")
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
