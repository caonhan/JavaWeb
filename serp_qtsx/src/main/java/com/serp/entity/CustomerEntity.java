package com.serp.entity;

import java.util.Date;

public class CustomerEntity {
	private int customerId;
	private String companyName;
	private String address;
	private String assignee;
	private String telePhone;
	private String mobilePhone;
	private String fax;
	private String email;
	private String website;
	private String createdBy;
	private Date createdDate;
	private String modified_by;
	private String description;
	
	
public CustomerEntity(int customerId, String companyName, String address, String assignee, String telePhone,
			String mobilePhone, String fax, String email, String website, String createdBy, Date createdDate,
			String modified_by, String description) {
		super();
		this.customerId = customerId;
		this.companyName = companyName;
		this.address = address;
		this.assignee = assignee;
		this.telePhone = telePhone;
		this.mobilePhone = mobilePhone;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modified_by = modified_by;
		this.description = description;
	}


public CustomerEntity(){
	super();
}


public int getCustomerId() {
	return customerId;
}


public void setCustomerId(int customerId) {
	this.customerId = customerId;
}


public String getCompanyName() {
	return companyName;
}


public void setCompanyName(String companyName) {
	this.companyName = companyName;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getAssignee() {
	return assignee;
}


public void setAssignee(String assignee) {
	this.assignee = assignee;
}


public String getTelePhone() {
	return telePhone;
}


public void setTelePhone(String telePhone) {
	this.telePhone = telePhone;
}


public String getMobilePhone() {
	return mobilePhone;
}


public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
}


public String getFax() {
	return fax;
}


public void setFax(String fax) {
	this.fax = fax;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getWebsite() {
	return website;
}


public void setWebsite(String website) {
	this.website = website;
}


public String getCreatedBy() {
	return createdBy;
}


public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}


public Date getCreatedDate() {
	return createdDate;
}


public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}


public String getModified_by() {
	return modified_by;
}


public void setModified_by(String modified_by) {
	this.modified_by = modified_by;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}









}
