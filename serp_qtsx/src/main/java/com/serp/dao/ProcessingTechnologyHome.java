package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.ProcessingTechnology;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ProcessingTechnology.
 * @see com.serp.dao.ProcessingTechnology
 * @author Hibernate Tools
 */
@Repository
@Transactional
public class ProcessingTechnologyHome {

	private static final Log log = LogFactory
			.getLog(ProcessingTechnologyHome.class);

	@Autowired
	private SessionFactory sessionFactory;


	public void persist(ProcessingTechnology transientInstance) {
		log.debug("persisting ProcessingTechnology instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProcessingTechnology instance) {
		log.debug("attaching dirty ProcessingTechnology instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProcessingTechnology instance) {
		log.debug("attaching clean ProcessingTechnology instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProcessingTechnology persistentInstance) {
		log.debug("deleting ProcessingTechnology instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProcessingTechnology merge(ProcessingTechnology detachedInstance) {
		log.debug("merging ProcessingTechnology instance");
		try {
			ProcessingTechnology result = (ProcessingTechnology) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProcessingTechnology findById(java.lang.Integer id) {
		log.debug("getting ProcessingTechnology instance with id: " + id);
		try {
			ProcessingTechnology instance = (ProcessingTechnology) sessionFactory
					.getCurrentSession().get(
							"com.serp.model.ProcessingTechnology", id);
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

	public List<ProcessingTechnology> findByExample(
			ProcessingTechnology instance) {
		log.debug("finding ProcessingTechnology instance by example");
		try {
			List<ProcessingTechnology> results = (List<ProcessingTechnology>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.dao.ProcessingTechnology")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
     * Create by LVDAT
     * Get all id of processing technology
     * return List<ProcessingTechnology>
     */
    @SuppressWarnings("unchecked")
    public List<ProcessingTechnology> getAllIdProcessingTechnology(){
        log.debug("getting all processing technology");
        try {
            List<ProcessingTechnology> results = (List<ProcessingTechnology>)sessionFactory.getCurrentSession().createQuery("from ProcessingTechnology").list();
            log.debug("getting all processing technology successfull, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("get all processing technology failed", re);
            throw re;
        }
    }
}
