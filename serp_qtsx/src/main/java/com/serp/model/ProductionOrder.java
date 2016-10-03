package com.serp.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "production_order", catalog = "serp")
public class ProductionOrder implements java.io.Serializable {
	

	@Override
	public String toString() {
		return "ProductionOrder [poId=" + poId + ", orders=" + orders + ", userByPoApprovedBy=" + userByPoApprovedBy
				+ ", userByPoFactoryManager=" + userByPoFactoryManager + ", poContent=" + poContent + ", poQuantity="
				+ poQuantity + ", poUnit=" + poUnit + ", poProcessTechnology=" + poProcessTechnology + ", poStatus="
				+ poStatus + ", poTimelength=" + poTimelength + ", poStarttime=" + poStarttime + ", poFinishtime="
				+ poFinishtime + "]";
	}

	private static final long serialVersionUID = 249558858157373232L;
	private Integer poId;
	private Orders orders;
	private User userByPoApprovedBy;
	private User userByPoFactoryManager;
	private String poContent;
	private Integer poQuantity;
	private String poUnit;
	private Boolean poProcessTechnology;
	private Integer poStatus;
	private Integer poTimelength;
	private Date poStarttime;
	private Date poFinishtime;

	public ProductionOrder() {
	}

	public ProductionOrder(Orders orders, User userByPoApprovedBy, User userByPoFactoryManager, String poContent,
			Integer poQuantity, String poUnit, Boolean poProcessTechnology, Integer poStatus, Integer poTimelength,
			Date poStarttime, Date poFinishtime) {
		this.orders = orders;
		this.userByPoApprovedBy = userByPoApprovedBy;
		this.userByPoFactoryManager = userByPoFactoryManager;
		this.poContent = poContent;
		this.poQuantity = poQuantity;
		this.poUnit = poUnit;
		this.poProcessTechnology = poProcessTechnology;
		this.poStatus = poStatus;
		this.poTimelength = poTimelength;
		this.poStarttime = poStarttime;
		this.poFinishtime = poFinishtime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "po_id", unique = true, nullable = false)
	public Integer getPoId() {
		return this.poId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_order_id")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_approved_by")
	public User getUserByPoApprovedBy() {
		return this.userByPoApprovedBy;
	}

	public void setUserByPoApprovedBy(User userByPoApprovedBy) {
		this.userByPoApprovedBy = userByPoApprovedBy;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_factory_manager")
	public User getUserByPoFactoryManager() {
		return this.userByPoFactoryManager;
	}

	public void setUserByPoFactoryManager(User userByPoFactoryManager) {
		this.userByPoFactoryManager = userByPoFactoryManager;
	}

	@Column(name = "po_content", length = 1000)
	public String getPoContent() {
		return this.poContent;
	}

	public void setPoContent(String poContent) {
		this.poContent = poContent;
	}

	@Column(name = "po_quantity")
	public Integer getPoQuantity() {
		return this.poQuantity;
	}

	public void setPoQuantity(Integer poQuantity) {
		this.poQuantity = poQuantity;
	}

	@Column(name = "po_unit", length = 50)
	public String getPoUnit() {
		return this.poUnit;
	}

	public void setPoUnit(String poUnit) {
		this.poUnit = poUnit;
	}

	@Column(name = "po_process_technology")
	public Boolean getPoProcessTechnology() {
		return this.poProcessTechnology;
	}

	public void setPoProcessTechnology(Boolean poProcessTechnology) {
		this.poProcessTechnology = poProcessTechnology;
	}

	@Column(name = "po_status", unique = true, nullable = false)
	public Integer getPoStatus() {
		return this.poStatus;
	}

	public void setPoStatus(Integer poStatus) {
		this.poStatus = poStatus;
	}

	@Column(name = "po_timelength")
	public Integer getPoTimelength() {
		return this.poTimelength;
	}

	public void setPoTimelength(Integer poTimelength) {
		this.poTimelength = poTimelength;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "po_starttime", length = 10)
	public Date getPoStarttime() {
		return this.poStarttime;
	}

	public void setPoStarttime(Date poStarttime) {
		this.poStarttime = poStarttime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "po_finishtime", length = 10)
	public Date getPoFinishtime() {
		return this.poFinishtime;
	}

	public void setPoFinishtime(Date poFinishtime) {
		this.poFinishtime = poFinishtime;
	}

}
