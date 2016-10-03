package com.serp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class StockRequisitionDetails.
 *
 * @author KhangNDD
 */
@Entity
@Table(name = "stock_requisition_details", catalog = "serp")
public class StockRequisitionDetails implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -167781732711561523L;
	
	/** The stock requisition details id. */
	private Integer stockRequisitionDetailsId;
	
	/** The material. */
	private Material material;
	
	/** The stock requisition. */
	private StockRequisition stockRequisition;
	
	/** The element. */
	private Element element;
	
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
	 * Instantiates a new stock requisition details.
	 */
	public StockRequisitionDetails() {
	}

	/**
	 * Instantiates a new stock requisition details.
	 *
	 * @param element the element
	 * @param stockRequisition the stock requisition
	 * @param phi the phi
	 * @param length the length
	 * @param width the width
	 * @param height the height
	 * @param quantity the quantity
	 */
	public StockRequisitionDetails(Element element, StockRequisition stockRequisition, double phi, double length,
			double width, double height, int quantity) {
		this.element = element;
		this.stockRequisition = stockRequisition;
		this.phi = phi;
		this.length = length;
		this.width = width;
		this.height = height;
		this.quantity = quantity;
	}

	/**
	 * Instantiates a new stock requisition details.
	 *
	 * @param element the element
	 * @param material the material
	 * @param stockRequisition the stock requisition
	 * @param phi the phi
	 * @param length the length
	 * @param width the width
	 * @param height the height
	 * @param quantity the quantity
	 * @param note the note
	 */
	public StockRequisitionDetails(Element element, Material material, StockRequisition stockRequisition, double phi,
			double length, double width, double height, int quantity, String note) {
		this.element = element;
		this.material = material;
		this.stockRequisition = stockRequisition;
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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_requisition_details_id", unique = true, nullable = false)
	public Integer getStockRequisitionDetailsId() {
		return this.stockRequisitionDetailsId;
	}

	/**
	 * Sets the stock requisition details id.
	 *
	 * @param stockRequisitionDetailsId the new stock requisition details id
	 */
	public void setStockRequisitionDetailsId(Integer stockRequisitionDetailsId) {
		this.stockRequisitionDetailsId = stockRequisitionDetailsId;
	}

	/**
	 * Gets the material.
	 *
	 * @return the material
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "material")
	public Material getMaterial() {
		return this.material;
	}

	/**
	 * Sets the material.
	 *
	 * @param material the new material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Gets the stock requisition.
	 *
	 * @return the stock requisition
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stock_requisition_id", nullable = false)
	public StockRequisition getStockRequisition() {
		return this.stockRequisition;
	}

	/**
	 * Sets the stock requisition.
	 *
	 * @param stockRequisition the new stock requisition
	 */
	public void setStockRequisition(StockRequisition stockRequisition) {
		this.stockRequisition = stockRequisition;
	}

	/**
	 * Gets the element.
	 *
	 * @return the element
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "name", nullable = false)
	public Element getElement() {
		return this.element;
	}

	/**
	 * Sets the element.
	 *
	 * @param element the new element
	 */
	public void setElement(Element element) {
		this.element = element;
	}

	/**
	 * Gets the phi.
	 *
	 * @return the phi
	 */
	@Column(name = "phi", nullable = false, precision = 22, scale = 0)
	public double getPhi() {
		return this.phi;
	}

	/**
	 * Sets the phi.
	 *
	 * @param phi the new phi
	 */
	public void setPhi(double phi) {
		if (phi < 0) {
			phi = 0;
		} else {
			this.phi = phi;
		}
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	@Column(name = "length", nullable = false, precision = 22, scale = 0)
	public double getLength() {
		return this.length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	@Column(name = "width", nullable = false, precision = 22, scale = 0)
	public double getWidth() {
		return this.width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	@Column(name = "height", nullable = false, precision = 22, scale = 0)
	public double getHeight() {
		return this.height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

}
