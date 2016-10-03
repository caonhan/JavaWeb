package com.serp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.StockRequisition;
import com.serp.model.StockRequisitionDetails;

/**
 * The Class StockRequisitionDAOImpl.
 *
 * @author KhangNDD
 */
@Repository("stockRequisitionDao")
@Transactional
public class StockRequisitionDAOImpl implements StockRequisitionDAO {

	/** The session factory. */
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(StockRequisitionDAOImpl.class.getSimpleName());

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory
	 *            the new session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.dao.StockRequisitionDAO#saveStockRequisition(com.serp.model.
	 * StockRequisition)
	 */
	@Override
	public void saveStockRequisition(StockRequisition sr) {
		log.debug("Saving or Updating StockRequisition instance with id: " + sr.getRequisitionId());

		try {
			// Set current date as last modified date
			sr.setLastModifiedDate(new Date());

			getSession().save(sr);

			Iterator<StockRequisitionDetails> iterator = sr.getStockRequisitionDetailses().iterator();
			while (iterator.hasNext()) {
				StockRequisitionDetails srd = iterator.next();

				// Set Stock Requisition with last modified date to Detail
				// object
				srd.setStockRequisition(sr);

				// Save or update detail to database
				saveOrUpdateStockRequisitionDetails(srd);
			}

			log.debug("Save successful");
		} catch (RuntimeException re) {
			log.error("Save failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.dao.StockRequisitionDAO#updateStockRequisition(com.serp.model.
	 * StockRequisition)
	 */
	@Override
	public void updateStockRequisition(StockRequisition sr) {
		log.debug("Saving or Updating StockRequisition instance with id: " + sr.getRequisitionId());

		try {
			// Set current date as last modified date
			sr.setLastModifiedDate(new Date());
			getSession().update(sr);

			Iterator<StockRequisitionDetails> iterator = sr.getStockRequisitionDetailses().iterator();
			while (iterator.hasNext()) {
				StockRequisitionDetails srd = iterator.next();

				// Set Stock Requisition with last modified date to Detail
				// object
				srd.setStockRequisition(sr);

				// Save or update detail to database
				saveOrUpdateStockRequisitionDetails(srd);
			}

			log.debug("Update successful");
		} catch (RuntimeException re) {
			log.error("Update failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.StockRequisitionDAO#getStockRequisitionById(java.lang.
	 * Integer)
	 */
	@Override
	public StockRequisition getStockRequisitionById(Integer id) {
		log.debug("getting StockRequisition instance with id: " + id);

		try {
			StockRequisition instance = (StockRequisition) getSession().get(StockRequisition.class, id);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.StockRequisitionDAO#deleteStockRequisition(java.lang.
	 * Integer)
	 */
	@Override
	public void deleteStockRequisition(Integer id) {
		log.debug("deleting StockRequisition instance");

		try {
			StockRequisition sRequisition = (StockRequisition) getSession().get(StockRequisition.class, id);
			getSession().delete(sRequisition);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.StockRequisitionDAO#getAllStockRequisitions()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StockRequisition> getAllStockRequisitions() {
		log.debug("finding all Stock Requisition instance");

		try {
			List<StockRequisition> srList = new ArrayList<StockRequisition>();

			// Select distinct from StockRequisition table (avoid duplicate)
			srList = getSession().createQuery("FROM " + StockRequisition.class.getName())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

			log.debug("find all successful, result size: " + srList.size());
			return srList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.dao.StockRequisitionDAO#saveOrUpdateStockRequisitionDetails(com.
	 * serp.model.StockRequisitionDetails)
	 */
	@Override
	public void saveOrUpdateStockRequisitionDetails(StockRequisitionDetails srd) {
		log.debug("Saving or Updating StockRequisitionDetails instance with id: " + srd.getStockRequisitionDetailsId());

		try {
			if (srd.getStockRequisitionDetailsId() == null || srd.getStockRequisitionDetailsId() < 0) {
				log.debug("Save detail id: " + srd.getStockRequisitionDetailsId());
				getSession().save(srd);
			} else {
				log.debug("Update detail id: " + srd.getStockRequisitionDetailsId());
				getSession().update(srd);
			}

			log.debug("Save or update successful");
		} catch (RuntimeException re) {
			log.error("Save or Update failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.dao.StockRequisitionDAO#deleteStockRequisitionDetails(java.lang.
	 * Integer)
	 */
	@Override
	public void deleteStockRequisitionDetails(Integer id) {
		log.debug("deleting StockRequisitionDetails instance with id: " + id);

		try {
			StockRequisitionDetails details = (StockRequisitionDetails) getSession().get(StockRequisitionDetails.class,
					id);
			getSession().delete(details);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.dao.StockRequisitionDAO#getAllDetailsByRequisitionId(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StockRequisitionDetails> getAllDetailsByRequisitionId(Integer id) {
		log.debug("finding all Stock Requisition Details by Stock Requisition id: " + id);

		try {
			List<StockRequisitionDetails> srList = new ArrayList<StockRequisitionDetails>();

			// Select distinct from StockRequisitionDetails table (avoid
			// duplicate)
			srList = getSession()
					.createQuery("FROM " + StockRequisitionDetails.class.getName()
							+ " WHERE stockRequisition.requisitionId = " + id)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

			log.debug("find all successful, result size: " + srList.size());
			return srList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.StockRequisitionDAO#getDetailById(java.lang.Integer)
	 */
	@Override
	public StockRequisitionDetails getDetailById(Integer id) {
		log.debug("getting StockRequisition instance with id: " + id);

		try {
			StockRequisitionDetails instance = (StockRequisitionDetails) getSession().get(StockRequisitionDetails.class,
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

}
