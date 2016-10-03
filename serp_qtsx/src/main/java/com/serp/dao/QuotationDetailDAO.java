package com.serp.dao;

import java.util.List;

import com.serp.entity.QuotationDetailEntity;
import com.serp.model.Quotationdetails;
/*
 * @author: SangNT
 */
public interface QuotationDetailDAO {
	public boolean persist(QuotationDetailEntity quotationDetails);
	public boolean saveOrUpdate(QuotationDetailEntity quotationDetails);
	public boolean delete(int quotationDetailsId);	
	List<Quotationdetails> allListQuotationDetail();
	QuotationDetailEntity findQuoDetailById(int quoDetailId);
}
