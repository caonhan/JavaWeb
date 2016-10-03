/**
 * 
 */
package com.serp.entity;

import com.serp.model.EstimateDetail;

/**
 * @author vuong-bt
 *
 */
public class EstimateDetailEntity {
	private Integer edId;
	private String element;
	private String material;
	private int edQuantity;
	private double edMaterialWeight;
	private double edPhi;
	private double edX;
	private double edY;
	private double edZ;
	private String eUnit;
	private double edMaterialCost;
	private double edLaborCost;
	private double edEquipmentCost;
	private double edToolCost;
	private double edExternalCost;
	private double edPrice;
	private double edTotalCost;

	/**
	 * 
	 */
	public EstimateDetailEntity() {
		super();
	}

	/**
	 * @param edId
	 * @param element
	 * @param estimate
	 * @param material
	 * @param edQuantity
	 * @param edMaterialWeight
	 * @param edPhi
	 * @param edX
	 * @param edY
	 * @param edZ
	 * @param eUnit
	 * @param edMaterialCost
	 * @param edLaborCost
	 * @param edEquipmentCost
	 * @param edToolCost
	 * @param edExternalCost
	 * @param edPrice
	 * @param edTotalCost
	 */
	public EstimateDetailEntity(Integer edId, String element, String material, int edQuantity, double edMaterialWeight,
			double edPhi, double edX, double edY, double edZ, String eUnit, double edMaterialCost, double edLaborCost,
			double edEquipmentCost, double edToolCost, double edExternalCost, double edPrice, double edTotalCost) {
		super();
		this.edId = edId;
		this.element = element;
		this.material = material;
		this.edQuantity = edQuantity;
		this.edMaterialWeight = edMaterialWeight;
		this.edPhi = edPhi;
		this.edX = edX;
		this.edY = edY;
		this.edZ = edZ;
		this.eUnit = eUnit;
		this.edMaterialCost = edMaterialCost;
		this.edLaborCost = edLaborCost;
		this.edEquipmentCost = edEquipmentCost;
		this.edToolCost = edToolCost;
		this.edExternalCost = edExternalCost;
		this.edPrice = edPrice;
		this.edTotalCost = edTotalCost;
	}

	public Integer getEdId() {
		return edId;
	}

	public void setEdId(Integer edId) {
		this.edId = edId;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getEdQuantity() {
		return edQuantity;
	}

	public void setEdQuantity(int edQuantity) {
		this.edQuantity = edQuantity;
	}

	public double getEdMaterialWeight() {
		return edMaterialWeight;
	}

	public void setEdMaterialWeight(double edMaterialWeight) {
		this.edMaterialWeight = edMaterialWeight;
	}

	public double getEdPhi() {
		return edPhi;
	}

	public void setEdPhi(double edPhi) {
		this.edPhi = edPhi;
	}

	public double getEdX() {
		return edX;
	}

	public void setEdX(double edX) {
		this.edX = edX;
	}

	public double getEdY() {
		return edY;
	}

	public void setEdY(double edY) {
		this.edY = edY;
	}

	public double getEdZ() {
		return edZ;
	}

	public void setEdZ(double edZ) {
		this.edZ = edZ;
	}

	public double getEdMaterialCost() {
		return edMaterialCost;
	}

	public void setEdMaterialCost(double edMaterialCost) {
		this.edMaterialCost = edMaterialCost;
	}

	public double getEdLaborCost() {
		return edLaborCost;
	}

	public void setEdLaborCost(double edLaborCost) {
		this.edLaborCost = edLaborCost;
	}

	public double getEdEquipmentCost() {
		return edEquipmentCost;
	}

	public void setEdEquipmentCost(double edEquipmentCost) {
		this.edEquipmentCost = edEquipmentCost;
	}

	public double getEdToolCost() {
		return edToolCost;
	}

	public void setEdToolCost(double edToolCost) {
		this.edToolCost = edToolCost;
	}

	public double getEdExternalCost() {
		return edExternalCost;
	}

	public void setEdExternalCost(double edExternalCost) {
		this.edExternalCost = edExternalCost;
	}

	public double getEdPrice() {
		return edPrice;
	}

	public void setEdPrice(double edPrice) {
		this.edPrice = edPrice;
	}

	public double getEdTotalCost() {
		return edTotalCost;
	}

	public void setEdTotalCost(double edTotalCost) {
		this.edTotalCost = edTotalCost;
	}

	public String geteUnit() {
		return eUnit;
	}

	public void seteUnit(String eUnit) {
		this.eUnit = eUnit;
	}
	public EstimateDetailEntity(EstimateDetail ed) {
		super();
		this.edId = ed.getEdId();
		this.element = ed.getElement().getEId();
		this.material = ed.getMaterial().getmaterialName();
		this.edQuantity = ed.getEdQuantity();
		this.edMaterialWeight = ed.getEdMaterialWeight();
		this.edPhi = ed.getEdPhi();
		this.edX = ed.getEdX();
		this.edY = ed.getEdY();
		this.edZ = ed.getEdZ();
		this.eUnit = ed.getElement().getEUnit();
		this.edMaterialCost = ed.getEdMaterialCost();
		this.edLaborCost = ed.getEdLaborCost();
		this.edEquipmentCost = ed.getEdEquipmentCost();
		this.edToolCost = ed.getEdToolCost();
		this.edExternalCost = ed.getEdExternalCost();
		this.edPrice = ed.getEdPrice();
		this.edTotalCost = ed.getEdTotalCost();
	}
	

}
