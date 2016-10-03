package com.serp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.Estimate;

/**
 * Home object for domain model class Estimate.
 * 
 * @see com.serp.model.Estimate
 * @author Hibernate Tools
 */
@Repository("estimateDAO")
@Transactional
public class EstimateDAOImpl implements EstimateDAO {

    private static final Log log = LogFactory.getLog(EstimateDAOImpl.class);

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void persist(Estimate transientInstance) {
	log.debug("persisting Estimate instance");
	try {
	    sessionFactory.getCurrentSession().persist(transientInstance);
	    log.debug("persist successful");
	} catch (Exception re) {
	    log.error("persist failed", re);
	    throw re;
	}
    }

    /**
     * this function is to save or update an estimate if it is already existed
     * 
     * @param instance
     */
    @Override
    public void saveOrUpdate(Estimate instance) {
	log.debug("attaching dirty Estimate instance");
	try {
	    sessionFactory.getCurrentSession().saveOrUpdate(instance);
	    log.debug("attach successful");
	} catch (Exception ex) {
	    log.fatal("Error in save or update estimate, message : " + ex.getMessage());
	    throw ex;
	}
    }

    /**
     * this function is to delete an estimate
     * 
     * @param estimate
     */
    @Override
    public void delete(Estimate persistentInstance) {
	log.debug("deleting Estimate instance");
	try {
	    sessionFactory.getCurrentSession().delete(persistentInstance);
	    log.debug("delete successful");
	} catch (Exception re) {
	    log.error("delete failed", re);
	    throw re;
	}
    }

    /**
     * this function is to find an estimate by id
     * 
     * @param id
     * @return estimate or error
     */

    @Override
    public Estimate findById(java.lang.Integer id) {
	log.debug("getting Estimate instance with id: " + id);
	try {
	    Estimate instance = (Estimate) sessionFactory.getCurrentSession().get("com.serp.model.Estimate", id);

	    if (instance == null) {
		log.debug("get successful, no instance found");
	    } else {
		log.debug("get successful, instance found");
	    }
	    return instance;
	} catch (Exception ex) {
	    log.fatal("find estimate error, message: " + ex);
	    throw ex;
	}
    }

    /**
     * this function is to list all estimate
     * 
     * @return list estimate
     */
    @Override
    public List<Estimate> list() {
	log.debug("in list Estimate");
	try {
	    @SuppressWarnings("unchecked")
	    List<Estimate> results = (List<Estimate>) sessionFactory.getCurrentSession()
		    .createQuery("FROM " + Estimate.class.getName()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		    .list();
	    log.debug("list successful, result size: " + results.size());
	    return results;
	} catch (Exception re) {
	    log.error("list failed", re);
	    throw re;
	}
    }
}
