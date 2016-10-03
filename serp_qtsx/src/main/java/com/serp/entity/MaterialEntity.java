package com.serp.entity;

import java.io.Serializable;

public class MaterialEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725675108599510125L;
	private String MId;
	private String MName;
	private double MPrice;

	public MaterialEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterialEntity(String mId, String mName, double mPrice) {
		super();
		MId = mId;
		MName = mName;
		MPrice = mPrice;
	}

	public String getMId() {
		return MId;
	}

	public void setMId(String mId) {
		MId = mId;
	}

	public String getMName() {
		return MName;
	}

	public void setMName(String mName) {
		MName = mName;
	}

	public double getMPrice() {
		return MPrice;
	}

	public void setMPrice(double mPrice) {
		MPrice = mPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MId == null) ? 0 : MId.hashCode());
		result = prime * result + ((MName == null) ? 0 : MName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(MPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MaterialEntity other = (MaterialEntity) obj;
		if (MId == null) {
			if (other.MId != null)
				return false;
		} else if (!MId.equals(other.MId))
			return false;
		if (MName == null) {
			if (other.MName != null)
				return false;
		} else if (!MName.equals(other.MName))
			return false;
		if (Double.doubleToLongBits(MPrice) != Double.doubleToLongBits(other.MPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaterialEntity [MId=" + MId + ", MName=" + MName + ", MPrice=" + MPrice + "]";
	}
}
