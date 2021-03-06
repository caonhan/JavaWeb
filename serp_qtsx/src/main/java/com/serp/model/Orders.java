package com.serp.model;

// Generated Apr 13, 2016 8:57:22 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orders generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "orders", catalog = "serp")
public class Orders implements java.io.Serializable {

	private String orderId;
	private Customer customer;
	private Status status;
	private User userByApprover;
	private User userByUserId;
	private String projectName;
	private Date createDate;
	private String orderContent;
	private Integer possibility;
	private String judgingContent;
	private String approvalContent;
	private String productName;
	private Integer amountOfProduct;
	private Date dueDate;
	private Set<LimitInventory> limitInventories = new HashSet<LimitInventory>(
			0);
	private Set<Estimate> estimates = new HashSet<Estimate>(0);
	private Set<ProcessingDocument> processingDocuments = new HashSet<ProcessingDocument>(
			0);
	private Set<StockRequisition> stockRequisitions = new HashSet<StockRequisition>(
			0);

	public Orders() {
	}

	public Orders(String orderId) {
		this.orderId = orderId;
	}

	public Orders(String orderId, Customer customer, Status status,
			User userByApprover, User userByUserId, String projectName,
			Date createDate, String orderContent, Integer possibility,
			String judgingContent, String approvalContent, String productName,
			Integer amountOfProduct, Date dueDate,
			Set<LimitInventory> limitInventories, Set<Estimate> estimates,
			Set<ProcessingDocument> processingDocuments,
			Set<StockRequisition> stockRequisitions) {
		this.orderId = orderId;
		this.customer = customer;
		this.status = status;
		this.userByApprover = userByApprover;
		this.userByUserId = userByUserId;
		this.projectName = projectName;
		this.createDate = createDate;
		this.orderContent = orderContent;
		this.possibility = possibility;
		this.judgingContent = judgingContent;
		this.approvalContent = approvalContent;
		this.productName = productName;
		this.amountOfProduct = amountOfProduct;
		this.dueDate = dueDate;
		this.limitInventories = limitInventories;
		this.estimates = estimates;
		this.processingDocuments = processingDocuments;
		this.stockRequisitions = stockRequisitions;
	}

	@Id
	@Column(name = "OrderID", unique = true, nullable = false, length = 50)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CustomerID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ApproveStatus")
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Approver")
	public User getUserByApprover() {
		return this.userByApprover;
	}

	public void setUserByApprover(User userByApprover) {
		this.userByApprover = userByApprover;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserID")
	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	@Column(name = "ProjectName", length = 50)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CreateDate", length = 10)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "OrderContent", length = 65535)
	public String getOrderContent() {
		return this.orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	@Column(name = "Possibility")
	public Integer getPossibility() {
		return this.possibility;
	}

	public void setPossibility(Integer possibility) {
		this.possibility = possibility;
	}

	@Column(name = "JudgingContent", length = 65535)
	public String getJudgingContent() {
		return this.judgingContent;
	}

	public void setJudgingContent(String judgingContent) {
		this.judgingContent = judgingContent;
	}

	@Column(name = "ApprovalContent", length = 65535)
	public String getApprovalContent() {
		return this.approvalContent;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	@Column(name = "ProductName", length = 50)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "AmountOfProduct")
	public Integer getAmountOfProduct() {
		return this.amountOfProduct;
	}

	public void setAmountOfProduct(Integer amountOfProduct) {
		this.amountOfProduct = amountOfProduct;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DueDate", length = 10)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<LimitInventory> getLimitInventories() {
		return this.limitInventories;
	}

	public void setLimitInventories(Set<LimitInventory> limitInventories) {
		this.limitInventories = limitInventories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Estimate> getEstimates() {
		return this.estimates;
	}

	public void setEstimates(Set<Estimate> estimates) {
		this.estimates = estimates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<ProcessingDocument> getProcessingDocuments() {
		return this.processingDocuments;
	}

	public void setProcessingDocuments(
			Set<ProcessingDocument> processingDocuments) {
		this.processingDocuments = processingDocuments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<StockRequisition> getStockRequisitions() {
		return this.stockRequisitions;
	}

	public void setStockRequisitions(Set<StockRequisition> stockRequisitions) {
		this.stockRequisitions = stockRequisitions;
	}

}
