package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.Function;

/**
 * Home object for domain model class Function.
 * @see com.serp.dao.Function
 * @author Hibernate Tools
 */
@Transactional
@Repository
public class FunctionDao {

	private static final Log log = LogFactory.getLog(FunctionDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This method get a function by it's id.
	 * @param id
	 * @return a function
	 */
	public Function findById(java.lang.String id) {
		log.debug("getting Function instance with id: " + id);
		try {
			Function instance = (Function) sessionFactory.getCurrentSession()
					.get("com.serp.model.Function", id);
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
	 * This method get all function from database
	 * @return List<Function>
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getAllFunction(){
		log.debug("getting all Role");
        try {
            List<Function> results = (List<Function>)sessionFactory.getCurrentSession().createQuery("from Function").list();
            log.debug("getting all Function successfull, result size: "
                    + results.size());
            
            return results;
        } catch (RuntimeException re) {
            log.error("get all Function failed", re);
            throw re;
        }
	}
}
