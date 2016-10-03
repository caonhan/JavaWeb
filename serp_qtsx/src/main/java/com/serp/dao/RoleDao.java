package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.Role;

/**
 * Home object for domain model class Role.
 * @see com.serp.dao.Role
 * @author Hibernate Tools
 */
@Transactional
@Repository
public class RoleDao {

	private static final Log log = LogFactory.getLog(RoleDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This function is used to save a role into database
	 * @param transientInstance
	 * Exception: catch RuntimeException
	 */
	public void persist(Role transientInstance) {
		log.debug("persisting Role instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * This function is used to update a role
	 * @param instance
	 */
	public void attachDirty(Role instance) {
		log.debug("attaching dirty Role instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * This function is used to delete a role
	 * @param persistentInstance
	 * Exception: catch RuntimeException
	 */
	public void delete(Role persistentInstance) {
		log.debug("deleting Role instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * This function get a role in database
	 * @param id
	 * @return a Role
	 */
	public Role findById(java.lang.String id) {
		log.debug("getting Role instance with id: " + id);
		try {
			Role instance = (Role) sessionFactory.getCurrentSession().get(
					"com.serp.model.Role", id);
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
	
	/**
	 * This function get all roles from database
	 * Exception: catch RuntimeException
	 * @return new List<Role>
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAllRole() {
        log.debug("getting all Role");
        try {
            List<Role> results = (List<Role>)sessionFactory.getCurrentSession().createQuery("from Role").list();
            log.debug("getting all Role successfull, result size: "
                    + results.size());
            
            return results;
        } catch (RuntimeException re) {
            log.error("get all Role failed", re);
            throw re;
        }
    }
}
