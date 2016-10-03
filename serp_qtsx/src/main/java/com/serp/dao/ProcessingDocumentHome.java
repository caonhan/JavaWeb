package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.serp.model.ProcessingDocument;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ProcessingDocument.
 * @see com.serp.dao.ProcessingDocument
 * @author Hibernate Tools
 */
public class ProcessingDocumentHome {

	private static final Log log = LogFactory
			.getLog(ProcessingDocumentHome.class);

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

	public void persist(ProcessingDocument transientInstance) {
		log.debug("persisting ProcessingDocument instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProcessingDocument instance) {
		log.debug("attaching dirty ProcessingDocument instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProcessingDocument instance) {
		log.debug("attaching clean ProcessingDocument instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProcessingDocument persistentInstance) {
		log.debug("deleting ProcessingDocument instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProcessingDocument merge(ProcessingDocument detachedInstance) {
		log.debug("merging ProcessingDocument instance");
		try {
			ProcessingDocument result = (ProcessingDocument) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProcessingDocument findById(int id) {
		log.debug("getting ProcessingDocument instance with id: " + id);
		try {
			ProcessingDocument instance = (ProcessingDocument) sessionFactory
					.getCurrentSession().get("com.serp.dao.ProcessingDocument",
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

	public List<ProcessingDocument> findByExample(ProcessingDocument instance) {
		log.debug("finding ProcessingDocument instance by example");
		try {
			List<ProcessingDocument> results = (List<ProcessingDocument>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.dao.ProcessingDocument")
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
