package com.serp.entity;

public class StatusEntity {
	private Integer statusId;
	private String statusName;
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
	public StatusEntity() {
		super();
	}
	
	public StatusEntity(Integer statusId, String statusName) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
	}
	
}
