package com.serp.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ElementEntity implements Serializable {

	private String EId;
	private String EName;
	private String EUnit;

	public ElementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ElementEntity(String eId, String eName, String eUnit) {
		super();
		EId = eId;
		EName = eName;
		EUnit = eUnit;
	}

	public String getEId() {
		return EId;
	}

	public void setEId(String eId) {
		EId = eId;
	}

	public String getEName() {
		return EName;
	}

	public void setEName(String eName) {
		EName = eName;
	}

	public String getEUnit() {
		return EUnit;
	}

	public void setEUnit(String eUnit) {
		EUnit = eUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EId == null) ? 0 : EId.hashCode());
		result = prime * result + ((EName == null) ? 0 : EName.hashCode());
		result = prime * result + ((EUnit == null) ? 0 : EUnit.hashCode());
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
		ElementEntity other = (ElementEntity) obj;
		if (EId == null) {
			if (other.EId != null)
				return false;
		} else if (!EId.equals(other.EId))
			return false;
		if (EName == null) {
			if (other.EName != null)
				return false;
		} else if (!EName.equals(other.EName))
			return false;
		if (EUnit == null) {
			if (other.EUnit != null)
				return false;
		} else if (!EUnit.equals(other.EUnit))
			return false;
		return true;
	}

}
