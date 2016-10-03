package com.serp.entity;

import java.io.Serializable;

/**
 * 
 * @author SangNT
 *
 */
@SuppressWarnings("serial")
public class QuotationDetailEntity implements Serializable{
	private String quotationId;
	private Integer quotationDetailsId;
	private String nameOfDetail;
	private Integer quantity;
	private String unit;
	private Double price;
	private Double amount;
	private String note;
	
	public String getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}
	public String getNameOfDetail() {
		return nameOfDetail;
	}
	public void setNameOfDetail(String nameOfDetail) {
		this.nameOfDetail = nameOfDetail;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getQuotationDetailsId() {
		return quotationDetailsId;
	}
	public void setQuotationDetailsId(Integer quotationDetailsId) {
		this.quotationDetailsId = quotationDetailsId;
	}
	
}
