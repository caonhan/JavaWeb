/**
 * 
 */
package com.serp.entity;

import java.util.Date;

import com.serp.model.Estimate;

/**
 * @author vuong-bt
 *
 */
public class EstimateEntity {
	private Integer esId;
	private String orders;
	private int status;
	private String userByEsApproverId;
	private String userByEsCreatorId;
	private Date esPublishedDate;
	private String esApproveContent;

	public Integer getEsId() {
		return esId;
	}

	public void setEsId(Integer esId) {
		this.esId = esId;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserByEsApproverId() {
		return userByEsApproverId;
	}

	public void setUserByEsApproverId(String userByEsApproverId) {
		this.userByEsApproverId = userByEsApproverId;
	}

	public String getUserByEsCreatorId() {
		return userByEsCreatorId;
	}

	public void setUserByEsCreatorId(String userByEsCreatorId) {
		this.userByEsCreatorId = userByEsCreatorId;
	}

	public Date getEsPublishedDate() {
		return esPublishedDate;
	}

	public void setEsPublishedDate(Date esPublishedDate) {
		this.esPublishedDate = esPublishedDate;
	}

	public String getEsApproveContent() {
		return esApproveContent;
	}

	public void setEsApproveContent(String esApproveContent) {
		this.esApproveContent = esApproveContent;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esApproveContent == null) ? 0 : esApproveContent.hashCode());
		result = prime * result + ((esId == null) ? 0 : esId.hashCode());
		result = prime * result + ((esPublishedDate == null) ? 0 : esPublishedDate.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + status;
		result = prime * result + ((userByEsApproverId == null) ? 0 : userByEsApproverId.hashCode());
		result = prime * result + ((userByEsCreatorId == null) ? 0 : userByEsCreatorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstimateEntity other = (EstimateEntity) obj;
		if (esApproveContent == null) {
			if (other.esApproveContent != null)
				return false;
		} else if (!esApproveContent.equals(other.esApproveContent))
			return false;
		if (esId == null) {
			if (other.esId != null)
				return false;
		} else if (!esId.equals(other.esId))
			return false;
		if (esPublishedDate == null) {
			if (other.esPublishedDate != null)
				return false;
		} else if (!esPublishedDate.equals(other.esPublishedDate))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (status != other.status)
			return false;
		if (userByEsApproverId == null) {
			if (other.userByEsApproverId != null)
				return false;
		} else if (!userByEsApproverId.equals(other.userByEsApproverId))
			return false;
		if (userByEsCreatorId == null) {
			if (other.userByEsCreatorId != null)
				return false;
		} else if (!userByEsCreatorId.equals(other.userByEsCreatorId))
			return false;
		return true;
	}

	/**
	 * @param esId
	 * @param orders
	 * @param status
	 * @param userByEsApproverId
	 * @param userByEsCreatorId
	 * @param esPublishedDate
	 * @param esApproveContent
	 */
	public EstimateEntity(Integer esId, String orders, int status, String userByEsApproverId,
			String userByEsCreatorId, Date esPublishedDate, String esApproveContent) {
		this.esId = esId;
		this.orders = orders;
		this.status = status;
		this.userByEsApproverId = userByEsApproverId;
		this.userByEsCreatorId = userByEsCreatorId;
		this.esPublishedDate = esPublishedDate;
		this.esApproveContent = esApproveContent;
	}

	public EstimateEntity(Estimate e) {
		this.esId = e.getEsId();
		this.orders = e.getOrders().getProjectName();
		this.status = e.getStatus().getStatusId();
		this.userByEsApproverId = e.getUserByEsApproverId().getUserId();
		this.userByEsCreatorId = e.getUserByEsCreatorId().getUserId();
		this.esPublishedDate = e.getEsPublishedDate();
		this.esApproveContent = e.getEsApproveContent();
	}

	public EstimateEntity() {
		super();
	}

}
