package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.serp.dao.QuotationDAO;
import com.serp.entity.QuotationEntity;
import com.serp.model.Estimate;
import com.serp.model.Quotation;

/*
 * @author: SangNT
 */

@Service("quotationService")
public class QuotationServiceImpl implements QuotationService {
	
	private static final Log logger = LogFactory.getLog(QuotationServiceImpl.class);
	
	@Autowired
	QuotationDAO quotationDAO;
	
	/* (Get all Quotation)
	 * @see com.serp.service.QuotationService#allListQuotation()
	 * return List<Quotation>
	 */
	@Override
	public List<Quotation> allListQuotation() {
		logger.info(String.format("Get all Quotation", getClass()));
		try{
			return this.quotationDAO.allListQuotation();
		}catch(Exception e){
			logger.error(String.format("Get all Quotation not success !! ", getClass(), e.getMessage()));
			throw e;
		}			
	}

	/* (Persist Quotation)
	 * @see com.serp.service.QuotationService#persist(com.serp.entity.QuotationEntity)
	 * return boolean.
	 */
	@Override
	public boolean persist(QuotationEntity quotation) {
		logger.info(String.format("Persist Quotation", getClass()));
		try{
			return quotationDAO.persist(quotation);
		}catch(Exception e){
			logger.error(String.format("Persist Quotation not success !! ", getClass(), e.getMessage()));
			throw e;
		}			
	}	

	/* (Get Quotation's list by quotationId)
	 * @see com.serp.service.QuotationService#findByEsId(java.lang.String)
	 * return List<Quotation>.
	 */
	@Override
	public List<Quotation> findByEsId(String quotationId) {
		logger.info(String.format("Get QuotationList by quotationId="+quotationId, getClass()));
		try{
			return this.quotationDAO.findByEsId(quotationId);
		}catch(Exception e){
			logger.error(String.format("Get QuotationList by quotationId= "+quotationId+" not success !! ", getClass(), e.getMessage()));
			throw e;
		}			
	}

	/* (Update Quotation's status)
	 * @see com.serp.service.QuotationService#updateStatus(com.serp.model.Quotation)
	 * return boolean.
	 */
	@Override
	public boolean updateStatus(Quotation quotation) {
		logger.info(String.format("Update Quotation's status", getClass()));
		try{
			return quotationDAO.updateStatus(quotation);
		}catch(Exception e){
			logger.error(String.format("Update Quotation's status not success !! ", getClass(), e.getMessage()));
			throw e;
		}			
	}

	/* (Update Quotation)
	 * @see com.serp.service.QuotationService#saveOrUpdate(com.serp.entity.QuotationEntity)
	 * return boolean.
	 */
	@Override
	public boolean saveOrUpdate(QuotationEntity quotation) {
		logger.info(String.format("Update Quotation", getClass()));
		try{
			return quotationDAO.saveOrUpdate(quotation);
		}catch(Exception e){
			logger.error(String.format("Update Quotation not success !! ", getClass(), e.getMessage()));
			throw e;
		}			
	}
	
	@Override
	public boolean updateContent(Estimate estimate) {
		return this.quotationDAO.updateContent(estimate);
	}
	@Override
	public List<Estimate> findByEstimateId(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("in estimate service find by id");
		try {
			return this.quotationDAO.findByEstimateId(id);
		} catch (NullPointerException ne) {
			logger.error("findbyid service estimate " + id + " err: " + ne.getMessage());
		} catch (Exception ex) {
			logger.fatal("findbyid fatal " + ex.getMessage());
		}
		return null;
	}
}
