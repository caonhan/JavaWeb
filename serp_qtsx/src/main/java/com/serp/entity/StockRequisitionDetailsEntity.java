package com.serp.entity;

import java.io.Serializable;

/**
 * The Class StockRequisitionDetailsEntity.
 *
 * @author KhangNDD
 */
@SuppressWarnings("serial")
public class StockRequisitionDetailsEntity implements Serializable {

	/** The stock requisition details id. */
	private Integer stockRequisitionDetailsId;

	/** The material id. */
	private String materialId;

	/** The material name. */
	private String materialName;

	/** The stock requisition id. */
	private Integer stockRequisitionId;

	/** The element id. */
	private String elementId;

	/** The element name. */
	private String elementName;

	/** The element unit. */
	private String elementUnit;

	/** The phi. */
	private double phi;

	/** The length. */
	private double length;

	/** The width. */
	private double width;

	/** The height. */
	private double height;

	/** The quantity. */
	private int quantity;

	/** The note. */
	private String note;

	/**
	 * Instantiates a new stock requisition details entity.
	 */
	public StockRequisitionDetailsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new stock requisition details entity.
	 *
	 * @param stockRequisitionDetailsId
	 *            the stock requisition details id
	 * @param materialId
	 *            the material id
	 * @param materialName
	 *            the material name
	 * @param stockRequisitionId
	 *            the stock requisition id
	 * @param elementId
	 *            the element id
	 * @param elementName
	 *            the element name
	 * @param elementUnit
	 *            the element unit
	 * @param phi
	 *            the phi
	 * @param length
	 *            the length
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param quantity
	 *            the quantity
	 * @param note
	 *            the note
	 */
	public StockRequisitionDetailsEntity(Integer stockRequisitionDetailsId, String materialId, String materialName,
			Integer stockRequisitionId, String elementId, String elementName, String elementUnit, double phi,
			double length, double width, double height, int quantity, String note) {
		super();
		this.stockRequisitionDetailsId = stockRequisitionDetailsId;
		this.materialId = materialId;
		this.materialName = materialName;
		this.stockRequisitionId = stockRequisitionId;
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

	/**
	 * Gets the stock requisition details id.
	 *
	 * @return the stock requisition details id
	 */
	public Integer getStockRequisitionDetailsId() {
		return stockRequisitionDetailsId;
	}

	/**
	 * Sets the stock requisition details id.
	 *
	 * @param stockRequisitionDetailsId
	 *            the new stock requisition details id
	 */
	public void setStockRequisitionDetailsId(Integer stockRequisitionDetailsId) {
		this.stockRequisitionDetailsId = stockRequisitionDetailsId;
	}

	/**
	 * Gets the material id.
	 *
	 * @return the material id
	 */
	public String getMaterialId() {
		return materialId;
	}

	/**
	 * Sets the material id.
	 *
	 * @param materialId
	 *            the new material id
	 */
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	/**
	 * Gets the material name.
	 *
	 * @return the material name
	 */
	public String getMaterialName() {
		return materialName;
	}

	/**
	 * Sets the material name.
	 *
	 * @param materialName
	 *            the new material name
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * Gets the stock requisition id.
	 *
	 * @return the stock requisition id
	 */
	public Integer getStockRequisitionId() {
		return stockRequisitionId;
	}

	/**
	 * Sets the stock requisition id.
	 *
	 * @param stockRequisitionId
	 *            the new stock requisition id
	 */
	public void setStockRequisitionId(Integer stockRequisitionId) {
		this.stockRequisitionId = stockRequisitionId;
	}

	/**
	 * Gets the element id.
	 *
	 * @return the element id
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * Sets the element id.
	 *
	 * @param elementId
	 *            the new element id
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
	 * Gets the element name.
	 *
	 * @return the element name
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * Sets the element name.
	 *
	 * @param elementName
	 *            the new element name
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	/**
	 * Gets the element unit.
	 *
	 * @return the element unit
	 */
	public String getElementUnit() {
		return elementUnit;
	}

	/**
	 * Sets the element unit.
	 *
	 * @param elementUnit
	 *            the new element unit
	 */
	public void setElementUnit(String elementUnit) {
		this.elementUnit = elementUnit;
	}

	/**
	 * Gets the phi.
	 *
	 * @return the phi
	 */
	public double getPhi() {
		return phi;
	}

	/**
	 * Sets the phi.
	 *
	 * @param phi
	 *            the new phi
	 */
	public void setPhi(double phi) {
		this.phi = phi;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length
	 *            the new length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width
	 *            the new width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height
	 *            the new height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity
	 *            the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note
	 *            the new note
	 */
	public void setNote(String note) {
		if (null == note) {
			this.note = "";
		} else {
			this.note = note;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
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
		result = prime * result + ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result + ((materialName == null) ? 0 : materialName.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		temp = Double.doubleToLongBits(phi);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((stockRequisitionDetailsId == null) ? 0 : stockRequisitionDetailsId.hashCode());
		result = prime * result + ((stockRequisitionId == null) ? 0 : stockRequisitionId.hashCode());
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockRequisitionDetailsEntity other = (StockRequisitionDetailsEntity) obj;
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
		if (stockRequisitionDetailsId == null) {
			if (other.stockRequisitionDetailsId != null)
				return false;
		} else if (!stockRequisitionDetailsId.equals(other.stockRequisitionDetailsId))
			return false;
		if (stockRequisitionId == null) {
			if (other.stockRequisitionId != null)
				return false;
		} else if (!stockRequisitionId.equals(other.stockRequisitionId))
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StockRequisitionDetailsEntity [stockRequisitionDetailsId=" + stockRequisitionDetailsId + ", materialId="
				+ materialId + ", materialName=" + materialName + ", stockRequisitionId=" + stockRequisitionId
				+ ", elementId=" + elementId + ", elementName=" + elementName + ", elementUnit=" + elementUnit
				+ ", phi=" + phi + ", length=" + length + ", width=" + width + ", height=" + height + ", quantity="
				+ quantity + ", note=" + note + "]";
	}

}
