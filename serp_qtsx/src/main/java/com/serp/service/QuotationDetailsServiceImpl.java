package com.serp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.QuotationDAO;
import com.serp.dao.QuotationDetailDAO;
import com.serp.entity.QuotationDetailEntity;
import com.serp.model.Quotationdetails;

/*
 * @author: SangNT
 */
@Service("quotationDetailsService")
public class QuotationDetailsServiceImpl implements QuotationDetailsService {
	
	private static final Log logger = LogFactory.getLog(QuotationDetailsServiceImpl.class);
	
	@Autowired
	private QuotationDetailDAO quotationDetailsHome;	
	QuotationDAO quotationDao;
	
	/* (Persist Quotation_Detail)
	 * @see com.serp.service.QuotationDetailsService#persist(com.serp.entity.QuotationDetailEntity)
	 */
	@Override
	public boolean persist(QuotationDetailEntity quotationDetails) {
		logger.info(String.format("Persist QuotationDetail", getClass()));
		try{
			return this.quotationDetailsHome.persist(quotationDetails);
		}catch(Exception e){
			logger.error(String.format("Persist QuotationDetail not success !! ", getClass(), e.getMessage()));
			throw e;
		}		
	}

	/* (Delete quotation_detail)
	 * @see com.serp.service.QuotationDetailsService#delete(com.serp.model.Quotationdetails)
	 */
	@Override
	public boolean delete(int quotationDetailsId) {
		logger.info(String.format("Delete QuotationDetail", getClass()));
		try{
			return this.quotationDetailsHome.delete(quotationDetailsId);
		}catch(Exception e){
			logger.error(String.format("Delete QuotationDetail not success !! ", getClass(), e.getMessage()));
			throw e;
		}				
	}		
	
	/* (Get all Quotation_Detail)
	 * @see com.serp.service.QuotationDetailsService#getAllQuotationDetailEntity()
	 */
	@Override
	public List<QuotationDetailEntity> getAllQuotationDetailEntity(){
		logger.info(String.format("Get all QuotationDetail", getClass()));
		try{
			List<Quotationdetails> lstQuo= quotationDetailsHome.allListQuotationDetail();
			
			QuotationDetailEntity en;
			List<QuotationDetailEntity> lst= new ArrayList<QuotationDetailEntity>();
			
			for (Quotationdetails quotationdetails : lstQuo) {
				en= new QuotationDetailEntity();
				en.setQuotationDetailsId(quotationdetails.getQuotationDetailsId());
				en.setQuotationId(quotationdetails.getQuotation().getQuotationId());
				en.setNameOfDetail(quotationdetails.getNameOfDetail());
				en.setPrice(quotationdetails.getPrice());
				en.setUnit(quotationdetails.getUnit());
				en.setAmount(quotationdetails.getAmount());
				en.setQuantity(quotationdetails.getQuantity());
				en.setNote(quotationdetails.getNote());
				lst.add(en);
			}			
			return lst;
		}catch(Exception e){
			logger.error(String.format("Get QuotationDetail not success !! ", getClass(), e.getMessage()));
			throw e;
		}			
	}

	/* (Update QuotationDetail)
	 * @see com.serp.service.QuotationService#saveOrUpdate(com.serp.entity.QuotationEntity)
	 * return boolean.
	 */
	@Override
	public boolean saveOrUpdate(QuotationDetailEntity quotationDetails) {
		logger.info(String.format("Update QuotationDetail", getClass()));
		try{
			return quotationDetailsHome.saveOrUpdate(quotationDetails);
		}catch(Exception e){
			logger.error(String.format("Update QuotationDetail not success !! ", getClass(), e.getMessage()));
			throw e;
		}					
	}

	/* (Get Quotation Detail By Id)
	 * @see com.serp.service.QuotationDetailsService#findQuoDetailById(int)
	 * return QuotationDetail
	 */
	@Override
	public QuotationDetailEntity findQuoDetailById(int quoDetailId) {
		logger.info(String.format("Get QuotationDetail", getClass()));
		try{
			return quotationDetailsHome.findQuoDetailById(quoDetailId);
		}catch(Exception e){
			logger.error(String.format("Get QuotationDetail not success !! ", getClass(), e.getMessage()));
			throw e;
		}	
		
	}
}
