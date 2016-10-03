package com.serp.entity;

import java.util.Date;

import com.serp.model.Role;

public class UserEntity {

	private String userID;
	private String password;
	private String name;
	private Date birthDate;
	private String phoneNumber;
	private String email;
	private String address;
	private String department;
	private String role_id;
	private Integer status;
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getRole_id() {
		return role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

		public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String userID, String password, String name, Date birthDate, String phoneNumber, String email,
			String address, String department,String role_id, Integer status) {
		super();
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.department = department;
		this.role_id = role_id;
		this.status = status;
	}
	
}
