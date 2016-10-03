package com.serp.model;
// Generated Apr 16, 2016 5:00:24 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Thanh Van
 * @version 1.0 Apr 13, 2016
 *
 */
@Entity
@Table(name = "material", catalog = "serp")
public class Material implements java.io.Serializable {

    private static final long serialVersionUID = 438410931103442247L;
    @Size(min = 1, max = 50)
    @NotEmpty(message = "ID must be something")
    private String materialId;
    @NotEmpty(message = "Name must be between 1 and 200 characters long")
    @Size(min = 1, max = 200)
    private String materialName;
    @Min(1000)
    @Max(999999999)
    private double materialPrice;
    private Set<StockRequisitionDetails> stockRequisitionDetailses = new HashSet<StockRequisitionDetails>(0);
    private Set<LimitInventoryDetail> limitInventoryDetails = new HashSet<LimitInventoryDetail>(0);
    private Set<EstimateDetail> estimateDetails = new HashSet<EstimateDetail>(0);

    /*
     * Contructor no paramater
     */
    public Material() {
    }

    /*
     * Contructor Material(String materialId, String materialName, double
     * materialPrice)
     */
    public Material(String materialId, String materialName, double materialPrice) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialPrice = materialPrice;
    }

    /*
     * Contructor Material(String materialId, String materialName, double
     * materialPrice, Set<StockRequisitionDetails> stockRequisitionDetailses,
     * Set<LimitInventoryDetail> limitInventoryDetails, Set<EstimateDetail>
     * estimateDetails)
     */
    public Material(String materialId, String materialName, double materialPrice,
            Set<StockRequisitionDetails> stockRequisitionDetailses, Set<LimitInventoryDetail> limitInventoryDetails,
            Set<EstimateDetail> estimateDetails) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialPrice = materialPrice;
        this.stockRequisitionDetailses = stockRequisitionDetailses;
        this.limitInventoryDetails = limitInventoryDetails;
        this.estimateDetails = estimateDetails;
    }

    @Id

    @Column(name = "m_id", unique = true, nullable = false, length = 50)
    public String getmaterialId() {
        return this.materialId;
    }

    public void setmaterialId(String MId) {
        this.materialId = MId;
    }

    @Column(name = "m_name", nullable = false, length = 200)
    public String getmaterialName() {
        return this.materialName;
    }

    public void setmaterialName(String MName) {
        this.materialName = MName;
    }

    @Column(name = "m_price", nullable = false, precision = 22, scale = 0)
    public double getmaterialPrice() {
        return this.materialPrice;
    }

    public void setmaterialPrice(double MPrice) {
        this.materialPrice = MPrice;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
    public Set<StockRequisitionDetails> getStockRequisitionDetailses() {
        return this.stockRequisitionDetailses;
    }

    public void setStockRequisitionDetailses(Set<StockRequisitionDetails> stockRequisitionDetailses) {
        this.stockRequisitionDetailses = stockRequisitionDetailses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
    public Set<LimitInventoryDetail> getLimitInventoryDetails() {
        return this.limitInventoryDetails;
    }

    public void setLimitInventoryDetails(Set<LimitInventoryDetail> limitInventoryDetails) {
        this.limitInventoryDetails = limitInventoryDetails;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
    public Set<EstimateDetail> getEstimateDetails() {
        return this.estimateDetails;
    }

    public void setEstimateDetails(Set<EstimateDetail> estimateDetails) {
        this.estimateDetails = estimateDetails;
    }

}
