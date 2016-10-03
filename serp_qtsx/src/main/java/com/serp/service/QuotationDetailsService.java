package com.serp.service;

import java.util.List;

import com.serp.entity.QuotationDetailEntity;

/*
 * @author: SangNT
 */
public interface QuotationDetailsService {
	public boolean persist(QuotationDetailEntity quotationDetails);
	public boolean saveOrUpdate(QuotationDetailEntity quotationDetails);
	public boolean delete(int quotationDetailsId);	
	public List<QuotationDetailEntity> getAllQuotationDetailEntity();
	QuotationDetailEntity findQuoDetailById(int quoDetailId);
}
