package com.serp.dao;

/**
 * @author ngocdiem
 */

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.Element;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;


@Repository()
@Transactional
public class TechnologyDaoImpl implements TechnologyDao{
	private static final Log log = LogFactory.getLog(TechnologyDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;


	@Override
	public List<ProcessingTechnologyDetail> list() {
		log.debug("in list Processing Technology");
		try {
			@SuppressWarnings("unchecked")
			List<ProcessingTechnologyDetail> results = sessionFactory.getCurrentSession()
					.createCriteria("com.serp.model.ProcessingTechnologyDetail").list();
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}


	@Override
	public void add(ProcessingTechnologyDetail processingTechnology) {
		log.debug("in list Processing Technology");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(processingTechnology);
			log.debug("Add: " +processingTechnology+"Success!");
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}


	@Override
	public void upadate(ProcessingTechnologyDetail technology) {
		log.debug("in list Processing Technology");
		try {
			
			sessionFactory.getCurrentSession().update(technology);
			log.debug("Update: " + technology + "Success!");
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
		
	}


	@Override
	public ProcessingTechnologyDetail findById(java.lang.Integer id) {
		log.debug("getting ProcessingTechnologyDetail instance with id: " + id);
		try {
			ProcessingTechnologyDetail instance = (ProcessingTechnologyDetail) sessionFactory.getCurrentSession().get(ProcessingTechnologyDetail.class, id);
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
	public void save(ProcessingTechnologyDetail technologyDetail) {
		log.debug("in list Processing Technology");
		try {
			sessionFactory.getCurrentSession().save(technologyDetail);
			log.debug("Add: " +technologyDetail+"Success!");
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}


	@Override
	public List<ProcessingTechnology> listProcessingTechnology() {
		log.debug("in list Processing Technology");
		try {
			@SuppressWarnings("unchecked")
			List<ProcessingTechnology> results = (List<ProcessingTechnology>) sessionFactory.getCurrentSession()
					.createCriteria("com.serp.model.ProcessingTechnology").list();
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}

}
