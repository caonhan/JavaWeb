package com.serp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.entity.QuotationEntity;
import com.serp.model.Estimate;
import com.serp.model.Quotation;
import com.serp.model.User;

/*
 * @author: SangNT
 */


@Repository("quotationDao")
@Transactional
public class QuotationDAOImpl implements QuotationDAO {
	
	private static final Log log = LogFactory.getLog(QuotationDAOImpl.class);
	
	@Autowired	
	SessionFactory sessionFactory;
	
	/* (Get all Quotation)
	 * @see com.serp.dao.QuotationDAO#allListQuotation()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> allListQuotation() {
		log.debug("in list Quotation");
		try {			
			List<Quotation> results = (List<Quotation>) sessionFactory.getCurrentSession()
					.createCriteria("com.serp.model.Quotation").list();
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}

	/* (persisting Quotation instance)
	 * @see com.serp.dao.QuotationDAO#persist(com.serp.entity.QuotationEntity)
	 */
	@Override
	public boolean persist(QuotationEntity quotation) {
		log.debug("persisting Quotation instance");
		try {
			Quotation quo=new Quotation();
			quo.setQuotationId(quotation.getQuotationId());
			quo.setEstimate(findByEsId(quotation.getEstimate()));
			sessionFactory.getCurrentSession().persist(quo);
			log.debug("persist successful");
			return true;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}	

	/* (List Quotation by quotationId)
	 * @see com.serp.dao.QuotationDAO#findByEsId(java.lang.String)
	 */
	@Override
	public List<Quotation> findByEsId(String quotationId) {
		log.debug("getting Quotation instance with id: " + quotationId);
		try {
			@SuppressWarnings("unchecked")
			List<Quotation> results = (List<Quotation>) sessionFactory.getCurrentSession().createQuery("from Quotation where quotationId="+quotationId).list();					
			log.debug("list successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/* (saving Quotation)
	 * @see com.serp.dao.QuotationDAO#saveOrUpdate(com.serp.entity.QuotationEntity)
	 */
	@Override
	public boolean saveOrUpdate(QuotationEntity quotation) {
		log.debug("saving Quotation instance");
		try {			
			Quotation quo=findById(quotation.getQuotationId());			
			quo.setNumOfDaysToComplete(quotation.getNumOfDaysToComplete());
			quo.setNumOfValidityDays(quotation.getNumOfValidityDays());
			quo.setPaymentMethod1(quotation.getPaymentMethod1());
			quo.setPaymentMethod2(quotation.getPaymentMethod2());
			quo.setStatus(3);
			quo.setUser(findByUserId(quotation.getUserId()));
			quo.setPublishDate(quotation.getPublishDate());
			quo.setAmount(quotation.getAmount());
			quo.setVat(quotation.getVat());
			quo.setTotalAmount(quotation.getTotalAmount());
			quo.setEstimate(findByEsId(quotation.getEstimate()));
			
			sessionFactory.getCurrentSession().saveOrUpdate(quo);
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	/* (updateStatus Quotation)
	 * @see com.serp.dao.QuotationDAO#updateStatus(com.serp.model.Quotation)
	 */
	@Override
	public boolean updateStatus(Quotation quotation) {
		log.debug("update Quotation's status instance");
		try {			
			Quotation quo=findById(quotation.getQuotationId());
			quo.setStatus(1);
			sessionFactory.getCurrentSession().saveOrUpdate(quo);
			log.debug("update status successful");
			return true;
		} catch (RuntimeException re) {
			log.error("update status failed", re);
			throw re;
		}
	}
	
	/* (findByEstimateId)
	 * @see com.serp.dao.QuotationDAO#findByEstimateId(java.lang.Integer)
	 */
	@Override
	public List<Estimate> findByEstimateId(Integer id) {
		log.debug("getting Estimate instance with id: " + id);
		try {
			@SuppressWarnings("unchecked")
			List<Estimate> results = (List<Estimate>) sessionFactory.getCurrentSession()
					.createQuery("from Estimate where esId=" + id).list();
			// .createCriteria("com.serp.model.Estimate").addQueryHint().list();
			log.debug("list successful, result size: " + results.size());

			return results;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (updateContent)
	 * @see com.serp.dao.QuotationDAO#updateContent(com.serp.model.Estimate)
	 */
	@Override
	public boolean updateContent(Estimate estimate) {
		log.debug("attaching dirty Role instance");
		try {
			Estimate es = findById(estimate.getEsId());
			es.setEsApproveContent("Yes");
			sessionFactory.getCurrentSession().saveOrUpdate(es);
			log.debug("attach successful");
			return true;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Estimate findById(java.lang.Integer id) {
		log.debug("getting Estimate instance with id: " + id);
		try {
			Estimate instance = (Estimate) sessionFactory.getCurrentSession().get("com.serp.model.Estimate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} 
	}
	
	/**
	 * @param id
	 * @return Quotation.
	 */
	public Quotation findById(java.lang.String id) {
		log.debug("getting Quotation instance with id: " + id);
		try {
			Quotation instance = (Quotation) sessionFactory.getCurrentSession().get(
					"com.serp.model.Quotation", id);
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
	 * @param id
	 * @return Estimate.
	 */
	public Estimate findByEsId(int id) {
		log.debug("getting Estimate instance with id: " + id);
		try {
			Estimate instance = (Estimate) sessionFactory.getCurrentSession().get(
					"com.serp.model.Estimate", id);			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * @param userId
	 * @return User.
	 */
	public User findByUserId(String userId) {
		log.debug("getting User instance with id: " + userId);
		try {
			User instance = (User) sessionFactory.getCurrentSession().get(
					"com.serp.model.User", userId);			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}
