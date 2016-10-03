package com.serp.model;

import static javax.persistence.GenerationType.IDENTITY;

// Generated Apr 13, 2016 8:57:22 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

/**
 * Estimate generated by hbm2java
 */
@Entity
@Table(name = "estimate", catalog = "serp")
@Proxy(lazy = false)
public class Estimate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer esId;
	@NotNull
	private Orders orders;
	@NotNull
	private Status status;
	private User userByEsApproverId;
	private User userByEsCreatorId;
	private Date esPublishedDate;
	@Size(min=0,max=200)
	private String esApproveContent;
	private Set<EstimateDetail> estimateDetail = new HashSet<EstimateDetail>(0);

	public Estimate() {
	}

	public Estimate(Orders orders, Status status, User userByEsApproverId, User userByEsCreatorId, Date esPublishedDate,
			String esApproveContent) {
		this.orders = orders;
		this.status = status;
		this.userByEsApproverId = userByEsApproverId;
		this.userByEsCreatorId = userByEsCreatorId;
		this.esPublishedDate = esPublishedDate;
		this.esApproveContent = esApproveContent;
	}

	public Estimate(Orders orders, Status status, User userByEsApproverId, User userByEsCreatorId, Date esPublishedDate,
			String esApproveContent, Set<Quotation> quotations, Set<EstimateDetail> estimateDetails) {
		this.orders = orders;
		this.status = status;
		this.userByEsApproverId = userByEsApproverId;
		this.userByEsCreatorId = userByEsCreatorId;
		this.esPublishedDate = esPublishedDate;
		this.esApproveContent = esApproveContent;
		this.estimateDetail = estimateDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "es_id", unique = true, nullable = false)
	public Integer getEsId() {
		return this.esId;
	}

	public void setEsId(Integer esId) {
		this.esId = esId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "es_order_id", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "es_status_id", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "es_approver_id", nullable = true)
	public User getUserByEsApproverId() {
		return this.userByEsApproverId;
	}

	public void setUserByEsApproverId(User userByEsApproverId) {
		this.userByEsApproverId = userByEsApproverId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "es_creator_id", nullable = false)
	public User getUserByEsCreatorId() {
		return this.userByEsCreatorId;
	}

	public void setUserByEsCreatorId(User userByEsCreatorId) {
		this.userByEsCreatorId = userByEsCreatorId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "es_published_date", nullable = false, length = 10)
	public Date getEsPublishedDate() {
		return this.esPublishedDate;
	}

	public void setEsPublishedDate(Date esPublishedDate) {
		this.esPublishedDate = esPublishedDate;
	}

	@Column(name = "es_approve_content", length = 65535)
	public String getEsApproveContent() {
		return this.esApproveContent;
	}

	public void setEsApproveContent(String esApproveContent) {
		this.esApproveContent = esApproveContent;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "estimate", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<EstimateDetail> getEstimateDetail() {
		return this.estimateDetail;
	}

	public void setEstimateDetail(Set<EstimateDetail> estimateDetail) {
		this.estimateDetail = estimateDetail;
	}

}
