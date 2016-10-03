package com.serp.service;

import java.util.List;

import com.serp.entity.QuotationEntity;
import com.serp.model.Estimate;
import com.serp.model.Quotation;
/*
 * @author: SangNT
 */
public interface QuotationService {
	public List<Quotation> allListQuotation();
	public boolean updateStatus(Quotation quotation);
	public boolean saveOrUpdate(QuotationEntity quotation);
	public boolean persist(QuotationEntity quotation);	
	List<Quotation> findByEsId(String quotationId);

	// Estimate
	List<Estimate> findByEstimateId(Integer id);
	boolean updateContent(Estimate estimate);
}
