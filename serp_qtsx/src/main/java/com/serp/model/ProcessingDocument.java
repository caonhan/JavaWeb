package com.serp.model;

// Generated Apr 13, 2016 8:57:22 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProcessingDocument generated by hbm2java
 */
@Entity
@Table(name = "processing_document", catalog = "serp")
public class ProcessingDocument implements java.io.Serializable {

	private int id;
	private int limitInventory;
	private String orders;
	private String processingTechnology;
	private String name;
	private Date createdDate;
	private String programNote;
	private String operationTraceNote;

	public ProcessingDocument() {
	}

	public ProcessingDocument(int id, int limitInventory,
	        String orders, Date createdDate, String operationTraceNote) {
		this.id = id;
		this.limitInventory = limitInventory;
		this.orders = orders;
		this.createdDate = createdDate;
		this.operationTraceNote = operationTraceNote;
	}

	public ProcessingDocument(int id, int limitInventory,
	        String orders, String processingTechnology,
			String name, Date createdDate, String programNote,
			String operationTraceNote) {
		this.id = id;
		this.limitInventory = limitInventory;
		this.orders = orders;
		this.processingTechnology = processingTechnology;
		this.name = name;
		this.createdDate = createdDate;
		this.programNote = programNote;
		this.operationTraceNote = operationTraceNote;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "limit_inventory_note", length = 11)
	public int getLimitInventory() {
		return this.limitInventory;
	}

	public void setLimitInventory(int limitInventory) {
		this.limitInventory = limitInventory;
	}

	@Column(name = "production_order", length = 11)
	public String getOrders() {
		return this.orders;
	}
	
	public void setOrders(String orders) {
		this.orders = orders;
	}

	@Column(name = "processing_technology_note", length = 11)
	public String getProcessingTechnology() {
		return this.processingTechnology;
	}

	public void setProcessingTechnology(
	        String processingTechnology) {
		this.processingTechnology = processingTechnology;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "program_note", length = 50)
	public String getProgramNote() {
		return this.programNote;
	}

	public void setProgramNote(String programNote) {
		this.programNote = programNote;
	}

	@Column(name = "operation_trace_note", nullable = false, length = 50)
	public String getOperationTraceNote() {
		return this.operationTraceNote;
	}

	public void setOperationTraceNote(String operationTraceNote) {
		this.operationTraceNote = operationTraceNote;
	}

}