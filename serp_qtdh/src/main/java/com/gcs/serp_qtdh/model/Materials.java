package com.gcs.serp_qtdh.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Materials generated by hbm2java
 */
@Entity
@Table(name = "materials", catalog = "serp_qtdh")
public class Materials implements java.io.Serializable {

	private Integer id;
	private Products products;
	private String materialId;
	private String materialName;
	private Double price;

	public Materials() {
	}

	public Materials(Products products, String materialId) {
		this.products = products;
		this.materialId = materialId;
	}

	public Materials(Products products, String materialId, String materialName, Double price) {
		this.products = products;
		this.materialId = materialId;
		this.materialName = materialName;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId", nullable = false)
	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@Column(name = "MaterialID", nullable = false, length = 50)
	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	@Column(name = "MaterialName", length = 10)
	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "Price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
