package com.serp.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author PhiTT
 *
 */
@SuppressWarnings("serial")
public class RoleEntity implements Serializable{
	private String roleId;
	private String ModifierId;
	private String CreatorId;
	private String roleName;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getModifierId() {
		return ModifierId;
	}
	public void setModifierId(String modifierId) {
		ModifierId = modifierId;
	}
	public String getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(String creatorId) {
		CreatorId = creatorId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "RoleEntity [roleId=" + roleId + ", ModifierId=" + ModifierId
				+ ", CreatorId=" + CreatorId + ", roleName=" + roleName
				+ ", description=" + description + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	public RoleEntity(String roleId, String modifierId, String creatorId,
			String roleName, String description, Date createdDate,
			Date modifiedDate) {
		super();
		this.roleId = roleId;
		ModifierId = modifierId;
		CreatorId = creatorId;
		this.roleName = roleName;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
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
		RoleEntity other = (RoleEntity) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
	public RoleEntity() {
		super();
	}
	
	
}
