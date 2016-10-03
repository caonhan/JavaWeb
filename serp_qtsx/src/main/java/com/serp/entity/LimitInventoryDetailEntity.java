package com.serp.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LimitInventoryDetailEntity implements Serializable {
	private Integer limitInventoryDetailsId;
	private String materialId;
	private String materialName;
	private Integer limitInventoryId;
	private String elementId;
	private String elementName;
	private String elementUnit;
	private double phi;
	private double length;
	private double width;
	private double height;
	private int quantity;
	private String note;

	public LimitInventoryDetailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitInventoryDetailEntity(Integer limitInventoryDetailsId, String materialId, String materialName,
			Integer limitInventoryId, String elementId, String elementName, String elementUnit, double phi,
			double length, double width, double height, int quantity, String note) {
		super();
		this.limitInventoryDetailsId = limitInventoryDetailsId;
		this.materialId = materialId;
		this.materialName = materialName;
		this.limitInventoryId = limitInventoryId;
		this.elementId = elementId;
		this.elementName = elementName;
		this.elementUnit = elementUnit;
		this.phi = phi;
		this.length = length;
		this.width = width;
		this.height = height;
		this.quantity = quantity;
		this.note = note;
	}

	public Integer getLimitInventoryDetailsId() {
		return limitInventoryDetailsId;
	}

	public void setLimitInventoryDetailsId(Integer limitInventoryDetailsId) {
		this.limitInventoryDetailsId = limitInventoryDetailsId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getLimitInventoryId() {
		return limitInventoryId;
	}

	public void setLimitInventoryId(Integer limitInventoryId) {
		this.limitInventoryId = limitInventoryId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementUnit() {
		return elementUnit;
	}

	public void setElementUnit(String elementUnit) {
		this.elementUnit = elementUnit;
	}

	public double getPhi() {
		return phi;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elementId == null) ? 0 : elementId.hashCode());
		result = prime * result + ((elementName == null) ? 0 : elementName.hashCode());
		result = prime * result + ((elementUnit == null) ? 0 : elementUnit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((limitInventoryDetailsId == null) ? 0 : limitInventoryDetailsId.hashCode());
		result = prime * result + ((limitInventoryId == null) ? 0 : limitInventoryId.hashCode());
		result = prime * result + ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result + ((materialName == null) ? 0 : materialName.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		temp = Double.doubleToLongBits(phi);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(width);
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
		LimitInventoryDetailEntity other = (LimitInventoryDetailEntity) obj;
		if (elementId == null) {
			if (other.elementId != null)
				return false;
		} else if (!elementId.equals(other.elementId))
			return false;
		if (elementName == null) {
			if (other.elementName != null)
				return false;
		} else if (!elementName.equals(other.elementName))
			return false;
		if (elementUnit == null) {
			if (other.elementUnit != null)
				return false;
		} else if (!elementUnit.equals(other.elementUnit))
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
			return false;
		if (limitInventoryDetailsId == null) {
			if (other.limitInventoryDetailsId != null)
				return false;
		} else if (!limitInventoryDetailsId.equals(other.limitInventoryDetailsId))
			return false;
		if (limitInventoryId == null) {
			if (other.limitInventoryId != null)
				return false;
		} else if (!limitInventoryId.equals(other.limitInventoryId))
			return false;
		if (materialId == null) {
			if (other.materialId != null)
				return false;
		} else if (!materialId.equals(other.materialId))
			return false;
		if (materialName == null) {
			if (other.materialName != null)
				return false;
		} else if (!materialName.equals(other.materialName))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (Double.doubleToLongBits(phi) != Double.doubleToLongBits(other.phi))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LimitInventoryDetailEntity [limitInventoryDetailsId=" + limitInventoryDetailsId + ", materialId="
				+ materialId + ", materialName=" + materialName + ", limitInventoryId=" + limitInventoryId
				+ ", elementId=" + elementId + ", elementName=" + elementName + ", elementUnit=" + elementUnit
				+ ", phi=" + phi + ", length=" + length + ", width=" + width + ", height=" + height + ", quantity="
				+ quantity + ", note=" + note + "]";
	}

	
}
