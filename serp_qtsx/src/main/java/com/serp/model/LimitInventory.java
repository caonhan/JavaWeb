package com.serp.model;
// Generated Apr 28, 2016 7:07:22 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LimitInventory generated by hbm2java
 */
@Entity
@Table(name = "limit_inventory", catalog = "serp")
public class LimitInventory implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5841120256309561687L;
	private Integer limitInventoryId;
	private Orders orders;
	private User userByCreator;
	private User userByDirectorateId;
	private User userByFactoryManagerId;
	private Date dateWanted;
	private Integer timeModify;
	private Date createdDate;
	private Date factoryManagerSignDate;
	private int factoryManagerStatus;
	private String factoryManagerComment;
	private Date directorateSignDate;
	private int directorateStatus;
	private String directorateComment;
	private Set<StockRequisition> stockRequisitions = new HashSet<StockRequisition>(0);
	private Set<ProcessingDocument> processingDocuments = new HashSet<ProcessingDocument>(0);
	private Set<LimitInventoryDetail> limitInventoryDetails = new HashSet<LimitInventoryDetail>(0);

	public LimitInventory() {
	}

	public LimitInventory(Orders orders, User userByCreator, Date createdDate, int factoryManagerStatus,
			int directorateStatus) {
		this.orders = orders;
		this.userByCreator = userByCreator;
		this.createdDate = createdDate;
		this.factoryManagerStatus = factoryManagerStatus;
		this.directorateStatus = directorateStatus;
	}

	public LimitInventory(Orders orders, User userByCreator, User userByDirectorateId, User userByFactoryManagerId,
			Date dateWanted, Integer timeModify, Date createdDate, Date factoryManagerSignDate,
			int factoryManagerStatus, String factoryManagerComment, Date directorateSignDate, int directorateStatus,
			String directorateComment, Set<StockRequisition> stockRequisitions,
			Set<ProcessingDocument> processingDocuments, Set<LimitInventoryDetail> limitInventoryDetails) {
		this.orders = orders;
		this.userByCreator = userByCreator;
		this.userByDirectorateId = userByDirectorateId;
		this.userByFactoryManagerId = userByFactoryManagerId;
		this.dateWanted = dateWanted;
		this.timeModify = timeModify;
		this.createdDate = createdDate;
		this.factoryManagerSignDate = factoryManagerSignDate;
		this.factoryManagerStatus = factoryManagerStatus;
		this.factoryManagerComment = factoryManagerComment;
		this.directorateSignDate = directorateSignDate;
		this.directorateStatus = directorateStatus;
		this.directorateComment = directorateComment;
		this.stockRequisitions = stockRequisitions;
		this.processingDocuments = processingDocuments;
		this.limitInventoryDetails = limitInventoryDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "limit_inventory_id", unique = true, nullable = false)
	public Integer getLimitInventoryId() {
		return this.limitInventoryId;
	}

	public void setLimitInventoryId(Integer limitInventoryId) {
		this.limitInventoryId = limitInventoryId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator", nullable = false)
	public User getUserByCreator() {
		return this.userByCreator;
	}

	public void setUserByCreator(User userByCreator) {
		this.userByCreator = userByCreator;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "directorate_id")
	public User getUserByDirectorateId() {
		return this.userByDirectorateId;
	}

	public void setUserByDirectorateId(User userByDirectorateId) {
		this.userByDirectorateId = userByDirectorateId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "factory_manager_id")
	public User getUserByFactoryManagerId() {
		return this.userByFactoryManagerId;
	}

	public void setUserByFactoryManagerId(User userByFactoryManagerId) {
		this.userByFactoryManagerId = userByFactoryManagerId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_wanted", length = 10)
	public Date getDateWanted() {
		return this.dateWanted;
	}

	public void setDateWanted(Date dateWanted) {
		this.dateWanted = dateWanted;
	}

	@Column(name = "time_modify")
	public Integer getTimeModify() {
		return this.timeModify;
	}

	public void setTimeModify(Integer timeModify) {
		this.timeModify = timeModify;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "factory_manager_sign_date", length = 10)
	public Date getFactoryManagerSignDate() {
		return this.factoryManagerSignDate;
	}

	public void setFactoryManagerSignDate(Date factoryManagerSignDate) {
		this.factoryManagerSignDate = factoryManagerSignDate;
	}

	@Column(name = "factory_manager_status", nullable = false)
	public int getFactoryManagerStatus() {
		return this.factoryManagerStatus;
	}

	public void setFactoryManagerStatus(int factoryManagerStatus) {
		this.factoryManagerStatus = factoryManagerStatus;
	}

	@Column(name = "factory_manager_comment")
	public String getFactoryManagerComment() {
		return this.factoryManagerComment;
	}

	public void setFactoryManagerComment(String factoryManagerComment) {
		this.factoryManagerComment = factoryManagerComment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "directorate_sign_date", length = 10)
	public Date getDirectorateSignDate() {
		return this.directorateSignDate;
	}

	public void setDirectorateSignDate(Date directorateSignDate) {
		this.directorateSignDate = directorateSignDate;
	}

	@Column(name = "directorate_status", nullable = false)
	public int getDirectorateStatus() {
		return this.directorateStatus;
	}

	public void setDirectorateStatus(int directorateStatus) {
		this.directorateStatus = directorateStatus;
	}

	@Column(name = "directorate_comment")
	public String getDirectorateComment() {
		return this.directorateComment;
	}

	public void setDirectorateComment(String directorateComment) {
		this.directorateComment = directorateComment;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "limitInventory")
	public Set<StockRequisition> getStockRequisitions() {
		return this.stockRequisitions;
	}

	public void setStockRequisitions(Set<StockRequisition> stockRequisitions) {
		this.stockRequisitions = stockRequisitions;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "limitInventory")
	public Set<ProcessingDocument> getProcessingDocuments() {
		return this.processingDocuments;
	}

	public void setProcessingDocuments(Set<ProcessingDocument> processingDocuments) {
		this.processingDocuments = processingDocuments;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "limitInventory")
	public Set<LimitInventoryDetail> getLimitInventoryDetails() {
		return this.limitInventoryDetails;
	}

	public void setLimitInventoryDetails(Set<LimitInventoryDetail> limitInventoryDetails) {
		this.limitInventoryDetails = limitInventoryDetails;
	}
	
}
