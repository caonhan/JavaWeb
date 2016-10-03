package com.serp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.Element;

@Repository("elementDao")
@Transactional
public class ElementDAOImpl implements ElementDAO {
	private static final Log log = LogFactory.getLog(ElementDAOImpl.class);

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
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

	@SuppressWarnings("deprecation")
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
			Element result = (Element) sessionFactory.getCurrentSession().merge(detachedInstance);
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
			Element instance = (Element) sessionFactory.getCurrentSession().get(Element.class, id);
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

	public List<Element> findAll() {
		log.debug("finding all Element instances");
		try {
			@SuppressWarnings("unchecked")
			List<Element> results = (List<Element>) sessionFactory.getCurrentSession().createCriteria(Element.class)
					.list();
			log.debug("find all successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}
