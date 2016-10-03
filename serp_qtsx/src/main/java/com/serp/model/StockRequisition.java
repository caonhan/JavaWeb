package com.serp.model;

import static javax.persistence.GenerationType.IDENTITY;

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

/**
 * The Class StockRequisition.
 *
 * @author KhangNDD
 */
@Entity
@Table(name = "stock_requisition", catalog = "serp")
public class StockRequisition implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6221133245827295778L;

	/** The requisition id. */
	private Integer requisitionId;

	/** The limit inventory. */
	private LimitInventory limitInventory;

	/** The orders. */
	private Orders orders;

	/** The user by creator. */
	private User userByCreator;

	/** The user by factory manager. */
	private User userByFactoryManager;

	/** The user by hfad. */
	private User userByHfad;

	/** The user by last modified user. */
	private User userByLastModifiedUser;

	/** The department. */
	private String department;

	/** The date wanted. */
	private Date dateWanted;

	/** The created date. */
	private Date createdDate;

	/** The recommend supplier. */
	private String recommendSupplier;

	/** The reason. */
	private String reason;

	/** The factory manager status. */
	private int factoryManagerStatus;

	/** The factory manager sign date. */
	private Date factoryManagerSignDate;

	/** The hfad status. */
	private int hfadStatus;

	/** The hfad sign date. */
	private Date hfadSignDate;

	/** The last modified date. */
	private Date lastModifiedDate;

	/** The factory manager comment. */
	private String factoryManagerComment;

	/** The hfad comment. */
	private String hfadComment;

	/** The stock requisition detailses. */
	private Set<StockRequisitionDetails> stockRequisitionDetailses = new HashSet<StockRequisitionDetails>(0);

	/**
	 * Instantiates a new stock requisition.
	 */
	public StockRequisition() {
	}

	/**
	 * Instantiates a new stock requisition.
	 *
	 * @param limitInventory
	 *            the limit inventory
	 * @param orders
	 *            the orders
	 * @param userByLastModifiedUser
	 *            the user by last modified user
	 * @param department
	 *            the department
	 * @param lastModifiedDate
	 *            the last modified date
	 */
	public StockRequisition(LimitInventory limitInventory, Orders orders, User userByLastModifiedUser,
			String department, Date lastModifiedDate) {
		this.limitInventory = limitInventory;
		this.orders = orders;
		this.userByLastModifiedUser = userByLastModifiedUser;
		this.department = department;
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Instantiates a new stock requisition.
	 *
	 * @param limitInventory
	 *            the limit inventory
	 * @param orders
	 *            the orders
	 * @param userByCreator
	 *            the user by creator
	 * @param userByLastModifiedUser
	 *            the user by last modified user
	 * @param department
	 *            the department
	 * @param createdDate
	 *            the created date
	 * @param lastModifiedDate
	 *            the last modified date
	 */
	public StockRequisition(LimitInventory limitInventory, Orders orders, User userByCreator,
			User userByLastModifiedUser, String department, Date createdDate, Date lastModifiedDate) {
		this.limitInventory = limitInventory;
		this.orders = orders;
		this.userByCreator = userByCreator;
		this.userByLastModifiedUser = userByLastModifiedUser;
		this.department = department;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Instantiates a new stock requisition.
	 *
	 * @param limitInventory
	 *            the limit inventory
	 * @param orders
	 *            the orders
	 * @param userByCreator
	 *            the user by creator
	 * @param userByLastModifiedUser
	 *            the user by last modified user
	 * @param department
	 *            the department
	 * @param createdDate
	 *            the created date
	 * @param factoryManagerStatus
	 *            the factory manager status
	 * @param hfadStatus
	 *            the hfad status
	 * @param lastModifiedDate
	 *            the last modified date
	 */
	public StockRequisition(LimitInventory limitInventory, Orders orders, User userByCreator,
			User userByLastModifiedUser, String department, Date createdDate, int factoryManagerStatus, int hfadStatus,
			Date lastModifiedDate) {
		this.limitInventory = limitInventory;
		this.orders = orders;
		this.userByCreator = userByCreator;
		this.userByLastModifiedUser = userByLastModifiedUser;
		this.department = department;
		this.createdDate = createdDate;
		this.factoryManagerStatus = factoryManagerStatus;
		this.hfadStatus = hfadStatus;
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Instantiates a new stock requisition.
	 *
	 * @param limitInventory
	 *            the limit inventory
	 * @param orders
	 *            the orders
	 * @param userByCreator
	 *            the user by creator
	 * @param userByFactoryManager
	 *            the user by factory manager
	 * @param userByHfad
	 *            the user by hfad
	 * @param userByLastModifiedUser
	 *            the user by last modified user
	 * @param department
	 *            the department
	 * @param dateWanted
	 *            the date wanted
	 * @param createdDate
	 *            the created date
	 * @param recommendSupplier
	 *            the recommend supplier
	 * @param reason
	 *            the reason
	 * @param factoryManagerStatus
	 *            the factory manager status
	 * @param factoryManagerSignDate
	 *            the factory manager sign date
	 * @param hfadStatus
	 *            the hfad status
	 * @param hfadSignDate
	 *            the hfad sign date
	 * @param lastModifiedDate
	 *            the last modified date
	 * @param factoryManagerComment
	 *            the factory manager comment
	 * @param hfadComment
	 *            the hfad comment
	 * @param stockRequisitionDetailses
	 *            the stock requisition detailses
	 */
	public StockRequisition(LimitInventory limitInventory, Orders orders, User userByCreator, User userByFactoryManager,
			User userByHfad, User userByLastModifiedUser, String department, Date dateWanted, Date createdDate,
			String recommendSupplier, String reason, int factoryManagerStatus, Date factoryManagerSignDate,
			int hfadStatus, Date hfadSignDate, Date lastModifiedDate, String factoryManagerComment, String hfadComment,
			Set<StockRequisitionDetails> stockRequisitionDetailses) {
		this.limitInventory = limitInventory;
		this.orders = orders;
		this.userByCreator = userByCreator;
		this.userByFactoryManager = userByFactoryManager;
		this.userByHfad = userByHfad;
		this.userByLastModifiedUser = userByLastModifiedUser;
		this.department = department;
		this.dateWanted = dateWanted;
		this.createdDate = createdDate;
		this.recommendSupplier = recommendSupplier;
		this.reason = reason;
		this.factoryManagerStatus = factoryManagerStatus;
		this.factoryManagerSignDate = factoryManagerSignDate;
		this.hfadStatus = hfadStatus;
		this.hfadSignDate = hfadSignDate;
		this.lastModifiedDate = lastModifiedDate;
		this.factoryManagerComment = factoryManagerComment;
		this.hfadComment = hfadComment;
		this.stockRequisitionDetailses = stockRequisitionDetailses;
	}

	/**
	 * Gets the requisition id.
	 *
	 * @return the requisition id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "requisition_id", unique = true, nullable = false)
	public Integer getRequisitionId() {
		return this.requisitionId;
	}

	/**
	 * Sets the requisition id.
	 *
	 * @param requisitionId
	 *            the new requisition id
	 */
	public void setRequisitionId(Integer requisitionId) {
		this.requisitionId = requisitionId;
	}

	/**
	 * Gets the limit inventory.
	 *
	 * @return the limit inventory
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "limit_inventory_id", nullable = false)
	public LimitInventory getLimitInventory() {
		return this.limitInventory;
	}

	/**
	 * Sets the limit inventory.
	 *
	 * @param limitInventory
	 *            the new limit inventory
	 */
	public void setLimitInventory(LimitInventory limitInventory) {
		this.limitInventory = limitInventory;
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders
	 *            the new orders
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	/**
	 * Gets the user by creator.
	 *
	 * @return the user by creator
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator", nullable = false)
	public User getUserByCreator() {
		return this.userByCreator;
	}

	/**
	 * Sets the user by creator.
	 *
	 * @param userByCreator
	 *            the new user by creator
	 */
	public void setUserByCreator(User userByCreator) {
		this.userByCreator = userByCreator;
	}

	/**
	 * Gets the user by factory manager.
	 *
	 * @return the user by factory manager
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "factory_manager")
	public User getUserByFactoryManager() {
		return this.userByFactoryManager;
	}

	/**
	 * Sets the user by factory manager.
	 *
	 * @param userByFactoryManager
	 *            the new user by factory manager
	 */
	public void setUserByFactoryManager(User userByFactoryManager) {
		this.userByFactoryManager = userByFactoryManager;
	}

	/**
	 * Gets the user by hfad.
	 *
	 * @return the user by hfad
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hfad")
	public User getUserByHfad() {
		return this.userByHfad;
	}

	/**
	 * Sets the user by hfad.
	 *
	 * @param userByHfad
	 *            the new user by hfad
	 */
	public void setUserByHfad(User userByHfad) {
		this.userByHfad = userByHfad;
	}

	/**
	 * Gets the user by last modified user.
	 *
	 * @return the user by last modified user
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "last_modified_user", nullable = false)
	public User getUserByLastModifiedUser() {
		return this.userByLastModifiedUser;
	}

	/**
	 * Sets the user by last modified user.
	 *
	 * @param userByLastModifiedUser
	 *            the new user by last modified user
	 */
	public void setUserByLastModifiedUser(User userByLastModifiedUser) {
		this.userByLastModifiedUser = userByLastModifiedUser;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	@Column(name = "department", nullable = false, length = 100)
	public String getDepartment() {
		return this.department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department
	 *            the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Gets the date wanted.
	 *
	 * @return the date wanted
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_wanted", length = 10)
	public Date getDateWanted() {
		return this.dateWanted;
	}

	/**
	 * Sets the date wanted.
	 *
	 * @param dateWanted
	 *            the new date wanted
	 */
	public void setDateWanted(Date dateWanted) {
		this.dateWanted = dateWanted;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the recommend supplier.
	 *
	 * @return the recommend supplier
	 */
	@Column(name = "recommend_supplier")
	public String getRecommendSupplier() {
		return this.recommendSupplier;
	}

	/**
	 * Sets the recommend supplier.
	 *
	 * @param recommendSupplier
	 *            the new recommend supplier
	 */
	public void setRecommendSupplier(String recommendSupplier) {
		this.recommendSupplier = recommendSupplier;
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	@Column(name = "reason")
	public String getReason() {
		return this.reason;
	}

	/**
	 * Sets the reason.
	 *
	 * @param reason
	 *            the new reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Gets the factory manager status.
	 *
	 * @return the factory manager status
	 */
	@Column(name = "factory_manager_status", nullable = false)
	public int getFactoryManagerStatus() {
		return this.factoryManagerStatus;
	}

	/**
	 * Sets the factory manager status.
	 *
	 * @param factoryManagerStatus
	 *            the new factory manager status
	 */
	public void setFactoryManagerStatus(int factoryManagerStatus) {
		this.factoryManagerStatus = factoryManagerStatus;
	}

	/**
	 * Gets the factory manager sign date.
	 *
	 * @return the factory manager sign date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "factory_manager_sign_date", length = 10)
	public Date getFactoryManagerSignDate() {
		return this.factoryManagerSignDate;
	}

	/**
	 * Sets the factory manager sign date.
	 *
	 * @param factoryManagerSignDate
	 *            the new factory manager sign date
	 */
	public void setFactoryManagerSignDate(Date factoryManagerSignDate) {
		this.factoryManagerSignDate = factoryManagerSignDate;
	}

	/**
	 * Gets the hfad status.
	 *
	 * @return the hfad status
	 */
	@Column(name = "hfad_status", nullable = false)
	public int getHfadStatus() {
		return this.hfadStatus;
	}

	/**
	 * Sets the hfad status.
	 *
	 * @param hfadStatus
	 *            the new hfad status
	 */
	public void setHfadStatus(int hfadStatus) {
		this.hfadStatus = hfadStatus;
	}

	/**
	 * Gets the hfad sign date.
	 *
	 * @return the hfad sign date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "hfad_sign_date", length = 10)
	public Date getHfadSignDate() {
		return this.hfadSignDate;
	}

	/**
	 * Sets the hfad sign date.
	 *
	 * @param hfadSignDate
	 *            the new hfad sign date
	 */
	public void setHfadSignDate(Date hfadSignDate) {
		this.hfadSignDate = hfadSignDate;
	}

	/**
	 * Gets the last modified date.
	 *
	 * @return the last modified date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date", nullable = false, length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	/**
	 * Sets the last modified date.
	 *
	 * @param lastModifiedDate
	 *            the new last modified date
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Gets the factory manager comment.
	 *
	 * @return the factory manager comment
	 */
	@Column(name = "factory_manager_comment")
	public String getFactoryManagerComment() {
		return this.factoryManagerComment;
	}

	/**
	 * Sets the factory manager comment.
	 *
	 * @param factoryManagerComment
	 *            the new factory manager comment
	 */
	public void setFactoryManagerComment(String factoryManagerComment) {
		this.factoryManagerComment = factoryManagerComment;
	}

	/**
	 * Gets the hfad comment.
	 *
	 * @return the hfad comment
	 */
	@Column(name = "hfad_comment")
	public String getHfadComment() {
		return this.hfadComment;
	}

	/**
	 * Sets the hfad comment.
	 *
	 * @param hfadComment
	 *            the new hfad comment
	 */
	public void setHfadComment(String hfadComment) {
		this.hfadComment = hfadComment;
	}

	/**
	 * Gets the stock requisition detailses.
	 *
	 * @return the stock requisition detailses
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stockRequisition", cascade = CascadeType.REMOVE)
	public Set<StockRequisitionDetails> getStockRequisitionDetailses() {
		return this.stockRequisitionDetailses;
	}

	/**
	 * Sets the stock requisition detailses.
	 *
	 * @param stockRequisitionDetailses
	 *            the new stock requisition detailses
	 */
	public void setStockRequisitionDetailses(Set<StockRequisitionDetails> stockRequisitionDetailses) {
		this.stockRequisitionDetailses = stockRequisitionDetailses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((dateWanted == null) ? 0 : dateWanted.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((factoryManagerComment == null) ? 0 : factoryManagerComment.hashCode());
		result = prime * result + ((factoryManagerSignDate == null) ? 0 : factoryManagerSignDate.hashCode());
		result = prime * result + factoryManagerStatus;
		result = prime * result + ((hfadComment == null) ? 0 : hfadComment.hashCode());
		result = prime * result + ((hfadSignDate == null) ? 0 : hfadSignDate.hashCode());
		result = prime * result + hfadStatus;
		result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		result = prime * result + ((limitInventory == null) ? 0 : limitInventory.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((recommendSupplier == null) ? 0 : recommendSupplier.hashCode());
		result = prime * result + ((requisitionId == null) ? 0 : requisitionId.hashCode());
		result = prime * result + ((stockRequisitionDetailses == null) ? 0 : stockRequisitionDetailses.hashCode());
		result = prime * result + ((userByCreator == null) ? 0 : userByCreator.hashCode());
		result = prime * result + ((userByFactoryManager == null) ? 0 : userByFactoryManager.hashCode());
		result = prime * result + ((userByHfad == null) ? 0 : userByHfad.hashCode());
		result = prime * result + ((userByLastModifiedUser == null) ? 0 : userByLastModifiedUser.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockRequisition other = (StockRequisition) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (dateWanted == null) {
			if (other.dateWanted != null)
				return false;
		} else if (!dateWanted.equals(other.dateWanted))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (factoryManagerComment == null) {
			if (other.factoryManagerComment != null)
				return false;
		} else if (!factoryManagerComment.equals(other.factoryManagerComment))
			return false;
		if (factoryManagerSignDate == null) {
			if (other.factoryManagerSignDate != null)
				return false;
		} else if (!factoryManagerSignDate.equals(other.factoryManagerSignDate))
			return false;
		if (factoryManagerStatus != other.factoryManagerStatus)
			return false;
		if (hfadComment == null) {
			if (other.hfadComment != null)
				return false;
		} else if (!hfadComment.equals(other.hfadComment))
			return false;
		if (hfadSignDate == null) {
			if (other.hfadSignDate != null)
				return false;
		} else if (!hfadSignDate.equals(other.hfadSignDate))
			return false;
		if (hfadStatus != other.hfadStatus)
			return false;
		if (lastModifiedDate == null) {
			if (other.lastModifiedDate != null)
				return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate))
			return false;
		if (limitInventory == null) {
			if (other.limitInventory != null)
				return false;
		} else if (!limitInventory.equals(other.limitInventory))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (recommendSupplier == null) {
			if (other.recommendSupplier != null)
				return false;
		} else if (!recommendSupplier.equals(other.recommendSupplier))
			return false;
		if (requisitionId == null) {
			if (other.requisitionId != null)
				return false;
		} else if (!requisitionId.equals(other.requisitionId))
			return false;
		if (stockRequisitionDetailses == null) {
			if (other.stockRequisitionDetailses != null)
				return false;
		} else if (!stockRequisitionDetailses.equals(other.stockRequisitionDetailses))
			return false;
		if (userByCreator == null) {
			if (other.userByCreator != null)
				return false;
		} else if (!userByCreator.equals(other.userByCreator))
			return false;
		if (userByFactoryManager == null) {
			if (other.userByFactoryManager != null)
				return false;
		} else if (!userByFactoryManager.equals(other.userByFactoryManager))
			return false;
		if (userByHfad == null) {
			if (other.userByHfad != null)
				return false;
		} else if (!userByHfad.equals(other.userByHfad))
			return false;
		if (userByLastModifiedUser == null) {
			if (other.userByLastModifiedUser != null)
				return false;
		} else if (!userByLastModifiedUser.equals(other.userByLastModifiedUser))
			return false;
		return true;
	}
}
