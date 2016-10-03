package com.serp.dao;

// Generated Apr 12, 2016 11:39:18 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.serp.model.Estimate;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Estimate.
 * @see com.serp.dao.Estimate
 * @author Hibernate Tools
 */
public class SerpEstimateHome {

	private static final Log log = LogFactory.getLog(SerpEstimateHome.class);

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

	public void persist(Estimate transientInstance) {
		log.debug("persisting Estimate instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Estimate instance) {
		log.debug("attaching dirty Estimate instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Estimate instance) {
		log.debug("attaching clean Estimate instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Estimate persistentInstance) {
		log.debug("deleting Estimate instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Estimate merge(Estimate detachedInstance) {
		log.debug("merging Estimate instance");
		try {
			Estimate result = (Estimate) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Estimate findById(java.lang.Integer id) {
		log.debug("getting Estimate instance with id: " + id);
		try {
			Estimate instance = (Estimate) sessionFactory
					.getCurrentSession().get("com.serp.dao.SerpEstimate", id);
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

	public List<Estimate> findByExample(Estimate instance) {
		log.debug("finding Estimate instance by example");
		try {
			List<Estimate> results = (List<Estimate>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.dao.SerpEstimate")
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
