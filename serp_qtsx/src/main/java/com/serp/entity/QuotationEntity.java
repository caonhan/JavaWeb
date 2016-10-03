package com.serp.entity;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class QuotationEntity implements Serializable{
	private String quotationId;
	private Integer estimate;
	private String userId;
	private String numOfValidityDays;
	private Integer numOfDaysToComplete;
	private String paymentMethod1;
	private String paymentMethod2;
	private Integer status;
	private Date publishDate;
	private Double amount;
	private Double vat;
	private Double totalAmount;
	private Integer quotationNo;
	
	public String getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}
	public Integer getEstimate() {
		return estimate;
	}
	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNumOfValidityDays() {
		return numOfValidityDays;
	}
	public void setNumOfValidityDays(String numOfValidityDays) {
		this.numOfValidityDays = numOfValidityDays;
	}
	public Integer getNumOfDaysToComplete() {
		return numOfDaysToComplete;
	}
	public void setNumOfDaysToComplete(Integer numOfDaysToComplete) {
		this.numOfDaysToComplete = numOfDaysToComplete;
	}
	public String getPaymentMethod1() {
		return paymentMethod1;
	}
	public void setPaymentMethod1(String paymentMethod1) {
		this.paymentMethod1 = paymentMethod1;
	}
	public String getPaymentMethod2() {
		return paymentMethod2;
	}
	public void setPaymentMethod2(String paymentMethod2) {
		this.paymentMethod2 = paymentMethod2;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getQuotationNo() {
		return quotationNo;
	}
	public void setQuotationNo(Integer quotationNo) {
		this.quotationNo = quotationNo;
	}
	
	
}
