package com.serp.dao;

// Generated Apr 12, 2016 11:39:18 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.serp.model.Element;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Element.
 * @see com.serp.dao.Element
 * @author Hibernate Tools
 */
public class SerpElementHome {

	private static final Log log = LogFactory.getLog(SerpElementHome.class);

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

	public void persist(Element transientInstance) {
		log.debug("persisting Element instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Element instance) {
		log.debug("attaching dirty Element instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Element instance) {
		log.debug("attaching clean Element instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Element persistentInstance) {
		log.debug("deleting Element instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Element merge(Element detachedInstance) {
		log.debug("merging Element instance");
		try {
			Element result = (Element) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Element findById(java.lang.String id) {
		log.debug("getting Element instance with id: " + id);
		try {
			Element instance = (Element) sessionFactory
					.getCurrentSession().get("com.serp.dao.SerpElement", id);
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

	public List<Element> findByExample(Element instance) {
		log.debug("finding Element instance by example");
		try {
			List<Element> results = (List<Element>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.dao.SerpElement")
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
