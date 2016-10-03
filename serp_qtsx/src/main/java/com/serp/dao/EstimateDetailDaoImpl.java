package com.serp.dao;

import static org.hibernate.criterion.Example.create;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.Estimate;
import com.serp.model.EstimateDetail;

/**
 * Home object for domain model class EstimateDetail.
 * 
 * @see com.serp.dao.EstimateDetail
 * @author Hibernate Tools
 */
@Transactional
@Repository
public class EstimateDetailDaoImpl implements EstimateDetailDao {

	private static final Log log = LogFactory.getLog(EstimateDetailDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

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
			EstimateDetail result = (EstimateDetail) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public EstimateDetail findById(Integer id) {
		log.debug("getting EstimateDetail instance with id: " + id);
		try {
			EstimateDetail instance = (EstimateDetail) sessionFactory.getCurrentSession()
					.get("com.serp.model.EstimateDetail", id);
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

	@SuppressWarnings("unchecked")
	public List<EstimateDetail> findByExample(EstimateDetail instance) {
		log.debug("finding EstimateDetail instance by example");
		try {
			List<EstimateDetail> results = (List<EstimateDetail>) sessionFactory.getCurrentSession()
					.createCriteria("com.serp.model.EstimateDetail").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.serp.dao.EstimateDetailDao#list()
	 */
	@Override
	public List<EstimateDetail> list() {
		log.debug("in list EstimateDetail");
		try {
			@SuppressWarnings("unchecked")
			List<EstimateDetail> results = (List<EstimateDetail>) sessionFactory.getCurrentSession()
					.createQuery("FROM " + EstimateDetail.class.getName()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}
}
