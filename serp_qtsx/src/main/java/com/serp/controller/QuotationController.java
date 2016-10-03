package com.serp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.serp.entity.QuotationDetailEntity;
import com.serp.entity.QuotationEntity;
import com.serp.model.Estimate;
import com.serp.model.Quotation;
import com.serp.service.EstimateService;
import com.serp.service.QuotationDetailsService;
import com.serp.service.QuotationService;
/*
 * @author: SangNT
 */
@Controller
public class QuotationController {

	private static final Logger logger = Logger.getLogger(QuotationController.class);
	
	@Autowired
	QuotationService quotationService;
	
	@Autowired
	QuotationDetailsService quotationDetailService;
	
	@Autowired
	EstimateService estimateService;
	
	public void setQuotationService(QuotationService quotationService) {
		this.quotationService = quotationService;
	}
	public void setEstimateService(EstimateService estimateService) {
		this.estimateService = estimateService;
	}
	
	/**
	 * Get list estimate
	 * @return List Estimate
	 */
	@RequestMapping(value = { "/quotation" }, method = RequestMethod.GET)
	public String listEstimate(HttpServletResponse responese, HttpServletRequest request) {
		logger.info("Show Estimate List");
		responese.setContentType("text/html");	
		request.setAttribute("estimateList", this.estimateService.list());				
		return "esList";
	}
	
	/**
	 * Get list quotation
	 * @return List Quotation
	 */
	@RequestMapping(value = { "/quotationList" }, method = RequestMethod.GET)
	public String listQuotation(HttpServletResponse responese, HttpServletRequest request) {
		logger.info("Show Quotation List");
		responese.setContentType("text/html");	
		request.setAttribute("quotationList", this.quotationService.allListQuotation());				
		return "quotationList";
	}
	
	/**
	 * Create quotation
	 * @return view quotation
	 */
	@RequestMapping(value = { "/createQuotation" }, method = RequestMethod.GET)
	public String createQuotation(HttpServletResponse responese, HttpServletRequest request) {
		logger.info("Show quotation view");
		responese.setContentType("text/html");	
		int estimateID=Integer.parseInt(request.getParameter("estimateId"));
		String quoId=request.getParameter("estimateId");
		request.setAttribute("estimate", this.quotationService.findByEstimateId(estimateID));
		String content=request.getParameter("approveContent");

		if("Yes".equals(content)){
			request.setAttribute("quotation", this.quotationService.findByEsId(request.getParameter("estimateId")));
		}
		if(!"Yes".equals(content)){
			
			QuotationEntity quo=new QuotationEntity();	
			quo.setQuotationId(quoId);
			quo.setEstimate(estimateID);
			
			Estimate es=new Estimate();
			es.setEsId(estimateID);
			
			this.quotationService.persist(quo);
			this.quotationService.updateContent(es);
			request.setAttribute("quotation", this.quotationService.findByEsId(quoId));
		}
		return "quotation";
	}
	
	/**
	 * Update quotation
	 * @param QuotationEntity quotationEntity
	 * @return Map<String, QuotationDetail>
	 */
	@RequestMapping(value = { "/updateQuotation" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> updateQuotation(@RequestBody QuotationEntity quotationEntity) {		
		logger.info("Ajax - update Quotation");
		Map<String, Object> result = new HashMap<String, Object>();					
		result.put("quotationEntity", quotationService.saveOrUpdate(quotationEntity));
		return result;
	}	
	
	/**
	 * Approve quotation
	 * @return "redirect:quotationList"( List quotation)
	 */
	@RequestMapping(value = { "/approveQuotation" }, method = RequestMethod.GET)
	public String approveQuotation(HttpServletResponse responese, HttpServletRequest request) {
		logger.info("Approve quotation");
		responese.setContentType("text/html");	
		String quotationId= request.getParameter("quotationId");
		Quotation quo=new Quotation();
		quo.setQuotationId(quotationId);		
		quotationService.updateStatus(quo);				
		return "redirect:quotationList";		
	}
	
	
	/**
	 * Get list quotationDetails
	 * @param String quotationId
	 * @return Map<String, QuotationDetail>
	 */
	@RequestMapping(value = { "/quotationDetail/{quotationId}" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> datailquotation(@PathVariable String quotationId) {	
		logger.info("Ajax - get list QuotationDetail");
		Map<String, Object> result = new HashMap<String, Object>();
		List<QuotationDetailEntity> ls=quotationDetailService.getAllQuotationDetailEntity();
		result.put("list",ls);	
		result.put("quotationId", quotationId);
		return result;
	}
	
	/**
	 * Create quotationDetails
	 * @param QuotationDetailEntity quotationDetail
	 * @return Map<String, QuotationDetail>
	 */
	@RequestMapping(value = { "/createQuotationDetail" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> createQuotationDetail(@RequestBody QuotationDetailEntity quotationDetail) {
		logger.info("Ajax - persist QuotationDetail");
		Map<String, Object> result = new HashMap<String, Object>();					
		result.put("list", quotationDetailService.persist(quotationDetail));
		return result;
	}
	
	/**
	 * Update quotationDetails
	 * @param QuotationDetailEntity quotationDetail
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = { "/updateQuotationDetail" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> updateQuotationDetail(@RequestBody QuotationDetailEntity quotationDetail) {
		logger.info("Ajax - update QuotationDetail");
		Map<String, Object> result = new HashMap<String, Object>();					
		result.put("list", quotationDetailService.saveOrUpdate(quotationDetail));
		return result;
	}
	
	/**
	 * Delete Quotationdetails by quotationDetailId
	 * @param int quotationDetailId
	 * @return boolean flag
	 */
	@RequestMapping(value = { "/deleteQuoDetail/{quotationDetailId}" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String deleteQuotationDetail(@PathVariable int quotationDetailId) {
		logger.info("Ajax - delete QuotationDetail by id: "+quotationDetailId);		
		boolean flag =quotationDetailService.delete(quotationDetailId);				
		return "{\"flag\":" + flag + "}";
	}
	
	/**
	 * Get Quotationdetails by quotationDetailId
	 * @param int quotationDetailId
	 * @return Map<String, Quotationdetails>
	 */
	@RequestMapping(value = { "/getQuoDetail/{quotationDetailId}" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getQuoDetail(@PathVariable int quotationDetailId) {
		logger.info("Ajax - delete QuotationDetail by id: "+quotationDetailId);
		Map<String, Object> result = new HashMap<String, Object>();
		QuotationDetailEntity quo=quotationDetailService.findQuoDetailById(quotationDetailId);
		result.put("quoD", quo);
		return result;
	}	
}
