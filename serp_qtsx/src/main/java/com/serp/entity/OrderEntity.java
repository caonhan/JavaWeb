package com.serp.entity;

import java.util.Date;

public class OrderEntity {
	
	private String orderId;
	private Integer customer;
	private Integer statusId;
	private String statusName;
	private String userByApprover;
	private String userByUserId;
	private String projectName;
	private Date createDate;
	private String orderContent;
	private Integer possibility;
	private String judgingContent;
	private String approvalContent;
	private String productName;
	private Integer amountOfProduct;
	private Date dueDate;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	public String getUserByApprover() {
		return userByApprover;
	}

	public void setUserByApprover(String userByApprover) {
		this.userByApprover = userByApprover;
	}

	public String getUserByUserId() {
		return userByUserId;
	}

	public void setUserByUserId(String userByUserId) {
		this.userByUserId = userByUserId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public Integer getPossibility() {
		return possibility;
	}

	public void setPossibility(Integer possibility) {
		this.possibility = possibility;
	}

	public String getJudgingContent() {
		return judgingContent;
	}

	public void setJudgingContent(String judgingContent) {
		this.judgingContent = judgingContent;
	}

	public String getApprovalContent() {
		return approvalContent;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getAmountOfProduct() {
		return amountOfProduct;
	}

	public void setAmountOfProduct(Integer amountOfProduct) {
		this.amountOfProduct = amountOfProduct;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public OrderEntity() {
		super();
	}

	public OrderEntity(String orderId, Integer customer, Integer statusId, String statusName, String userByApprover,
			String userByUserId, String projectName, Date createDate, String orderContent, Integer possibility,
			String judgingContent, String approvalContent, String productName, Integer amountOfProduct, Date dueDate) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.statusId = statusId;
		this.statusName = statusName;
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
	}
	
	



	
	

}
