package com.serp.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FunctionEntity implements Serializable{
	private String functionId;
	private String functionName;
	private String description;
	
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FunctionEntity(String functionId, String functionName,
			String description) {
		super();
		this.functionId = functionId;
		this.functionName = functionName;
		this.description = description;
	}
	public FunctionEntity() {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result
				+ ((functionName == null) ? 0 : functionName.hashCode());
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
		FunctionEntity other = (FunctionEntity) obj;
		if (functionId == null) {
			if (other.functionId != null)
				return false;
		} else if (!functionId.equals(other.functionId))
			return false;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FunctionEntity [functionId=" + functionId + ", functionName="
				+ functionName + ", description=" + description + "]";
	}
	
	
}
