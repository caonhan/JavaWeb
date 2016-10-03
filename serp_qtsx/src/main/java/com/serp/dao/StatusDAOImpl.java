package com.serp.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.Status;

/**
 * Home object for domain model class Status.
 * 
 * @see com.serp.dao.Status
 * @author Hibernate Tools
 */
@Repository()
@Transactional
public class StatusDAOImpl implements StatusDAO {

	private static final Log log = LogFactory.getLog(StatusDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;
/**
 * this function is to list all status
 * @return list status
 */
	@Override
	public List<Status> list() {
		log.debug("list Status");
		try {
			@SuppressWarnings("unchecked")
			List<Status> results = (List<Status>) sessionFactory.getCurrentSession().createCriteria(Status.class)
					.list();
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}
/**
 * this function is to find a status by id
 * @param id
 * @return status or null
 */
	@Override
	public Status findById(java.lang.Integer id) {
		log.debug("getting status instance with id: " + id);
		try {
			Status instance = (Status) sessionFactory.getCurrentSession().get("com.serp.model.Status", id);

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
}
