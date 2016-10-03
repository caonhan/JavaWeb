package com.serp.model;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Status generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "status", catalog = "serp")
public class Status implements java.io.Serializable {

	private Integer statusId;
	private String statusName;
	public Status() {
	}

	public Status(String statusName) {
		this.statusName = statusName;
	}
	


	@Override
	public String toString() {
		return statusName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "status_name", nullable = false, length = 50)
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	

}
