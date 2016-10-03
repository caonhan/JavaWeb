package com.serp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.entity.QuotationDetailEntity;
import com.serp.model.Quotation;
import com.serp.model.Quotationdetails;
/*
 * @author: SangNT
 */

@Repository("quotationDetailDao")
@Transactional
public class QuotationDetailDAOImpl implements QuotationDetailDAO {
	
	private static final Log log = LogFactory.getLog(QuotationDetailDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
		
	/* (Persist QuotationDetail)
	 * @see com.serp.dao.QuotationDetailDAO#persist(com.serp.entity.QuotationDetailEntity)
	 */
	@Override
	public boolean persist(QuotationDetailEntity quotationDetails) {		
		log.debug("persisting QuotationDetail instance");
		try {
			Quotationdetails quodetail =new Quotationdetails();
			quodetail.setQuotation(findById(quotationDetails.getQuotationId()));
			quodetail.setNameOfDetail(quotationDetails.getNameOfDetail());
			quodetail.setQuantity(quotationDetails.getQuantity());
			quodetail.setPrice(quotationDetails.getPrice());
			quodetail.setAmount(quotationDetails.getAmount());
			quodetail.setNote(quotationDetails.getNote());
			quodetail.setUnit(quotationDetails.getUnit());
			sessionFactory.getCurrentSession().persist(quodetail);			
			log.debug("persist successful");
			return true;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/* (saving QuotationDetail)
	 * @see com.serp.dao.QuotationDetailDAO#saveOrUpdate(com.serp.entity.QuotationDetailEntity)
	 */
	@Override
	public boolean saveOrUpdate(QuotationDetailEntity quotationDetails) {
		log.debug("saving QuotationDetail instance");
		try {			
			Quotationdetails quo=findByQuoDetailId(quotationDetails.getQuotationDetailsId());			
			quo.setNameOfDetail(quotationDetails.getNameOfDetail());
			quo.setQuantity(quotationDetails.getQuantity());
			quo.setPrice(quotationDetails.getPrice());
			quo.setUnit(quotationDetails.getUnit());
			quo.setNote(quotationDetails.getNote());
			quo.setAmount(quotationDetails.getAmount());
			quo.setQuotation(findById(quotationDetails.getQuotationId()));			
			sessionFactory.getCurrentSession().saveOrUpdate(quo);
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	/* (Delete Quotation)
	 * @see com.serp.dao.QuotationDetailDAO#delete(com.serp.model.Quotationdetails)
	 */
	@Override
	public boolean delete(int quotationDetailsId) {		
		log.debug("deleting Quotationdetails instance");
		try {
			Quotationdetails quodetail =findByQuoDetailId(quotationDetailsId);
			sessionFactory.getCurrentSession().delete(quodetail);
			log.debug("delete successful");
			return true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}	
	
	/* (List QuotationDetail)
	 * @see com.serp.dao.QuotationDetailDAO#allListQuotationDetail()
	 */
	@Override
	public List<Quotationdetails> allListQuotationDetail() {
		log.debug("List Quotationdetails");
		try {			
			@SuppressWarnings("unchecked")
			List<Quotationdetails> results = (List<Quotationdetails>) sessionFactory.getCurrentSession()
					.createQuery("from Quotationdetails").list();
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}
	
	/* (Get QuotationDetailEntity)
	 * @see com.serp.dao.QuotationDetailDAO#findQuoDetailById(int)
	 */
	@Override
	public QuotationDetailEntity findQuoDetailById(int quoDetailId) {
		log.debug("getting Quotationdetails instance with id: " + quoDetailId);
		try {
			Quotationdetails instance = (Quotationdetails) sessionFactory.getCurrentSession().get(
					"com.serp.model.Quotationdetails", quoDetailId);
			QuotationDetailEntity quoEn=new QuotationDetailEntity();
			quoEn.setNameOfDetail(instance.getNameOfDetail());
			quoEn.setQuantity(instance.getQuantity());
			quoEn.setPrice(instance.getPrice());
			quoEn.setNote(instance.getNote());
			quoEn.setUnit(instance.getUnit());
			quoEn.setQuotationId(instance.getQuotation().getQuotationId());
			quoEn.setQuotationDetailsId(instance.getQuotationDetailsId());
			log.debug("get successful, instance found");
			log.debug("getting Quotationdetails instance with id: " + quoDetailId);
			return quoEn;			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * @param id: quotationId
	 * @return Quotation
	 */
	public Quotation findById(java.lang.String id) {
		log.debug("getting Quotation instance with id: " + id);
		try {
			Quotation instance = (Quotation) sessionFactory.getCurrentSession().get(
					"com.serp.model.Quotation", id);			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * @param id: quotationDetatilId
	 * @return Quotationdetails
	 */
	public Quotationdetails findByQuoDetailId(int id) {
		log.debug("getting Quotationdetails instance with id: " + id);
		try {
			Quotationdetails instance = (Quotationdetails) sessionFactory.getCurrentSession().get(
					"com.serp.model.Quotationdetails", id);			
			log.debug("getting Quotationdetails instance with id: " + id);
			return instance;			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
