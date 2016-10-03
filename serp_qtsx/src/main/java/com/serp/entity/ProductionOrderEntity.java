package com.serp.entity;

import java.util.Date;

import com.serp.model.Orders;
import com.serp.model.User;

/**
 * 
 * @author LienKT
 * Class define id of production order 
 *  -- use in Processing Document services
 *
 */
public class ProductionOrderEntity{
    private int id;
    private String orders;
    private String userByPoApprovedBy;
    private String userByPoFactoryManager;
    private String poContent;
    private Integer poQuantity;
    private String poUnit;
    private Boolean poProcessTechnology;
    private Integer poStatus;
    private Integer poTimelength;
    private Date poStarttime;
    private Date poFinishtime;

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getUserByPoApprovedBy() {
        return userByPoApprovedBy;
    }

    public void setUserByPoApprovedBy(String userByPoApprovedBy) {
        this.userByPoApprovedBy = userByPoApprovedBy;
    }

    public String getUserByPoFactoryManager() {
        return userByPoFactoryManager;
    }

    public void setUserByPoFactoryManager(String userByPoFactoryManager) {
        this.userByPoFactoryManager = userByPoFactoryManager;
    }

    public String getPoContent() {
        return poContent;
    }

    public void setPoContent(String poContent) {
        this.poContent = poContent;
    }

    public Integer getPoQuantity() {
        return poQuantity;
    }

    public void setPoQuantity(Integer poQuantity) {
        this.poQuantity = poQuantity;
    }

    public String getPoUnit() {
        return poUnit;
    }

    public void setPoUnit(String poUnit) {
        this.poUnit = poUnit;
    }

    public Boolean getPoProcessTechnology() {
        return poProcessTechnology;
    }

    public void setPoProcessTechnology(Boolean poProcessTechnology) {
        this.poProcessTechnology = poProcessTechnology;
    }

    public Integer getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(Integer poStatus) {
        this.poStatus = poStatus;
    }

    public Integer getPoTimelength() {
        return poTimelength;
    }

    public void setPoTimelength(Integer poTimelength) {
        this.poTimelength = poTimelength;
    }

    public Date getPoStarttime() {
        return poStarttime;
    }

    public void setPoStarttime(Date poStarttime) {
        this.poStarttime = poStarttime;
    }

    public Date getPoFinishtime() {
        return poFinishtime;
    }

    public void setPoFinishtime(Date poFinishtime) {
        this.poFinishtime = poFinishtime;
    }

    public int getPOId() {
        return id;
    }

    public void setPOId(int POId) {
        this.id = POId;
    }
    
    public ProductionOrderEntity() {
        super();
    }
    
    public ProductionOrderEntity(int id) {
        super();
        this.id = id;
    }
    
    public ProductionOrderEntity(int id,String orders, String userByPoApprovedBy, String userByPoFactoryManager,String poContent,Integer poQuantity,
                                 String poUnit,Boolean poProcessTechnology,Integer poStatus, Integer poTimelength, Date poStarttime, Date poFinishtime){
        super();
        this.id = id;
        this.orders = orders;
        this.userByPoApprovedBy = userByPoApprovedBy;
        this.userByPoFactoryManager = userByPoFactoryManager;
        this.poContent = poContent;
        this.poQuantity = poQuantity;
        this.poFinishtime = poFinishtime;
        this.poUnit = poUnit;
        this.poProcessTechnology = poProcessTechnology;
        this.poStatus = poStatus;
        this.poTimelength = poTimelength;
        this.poStarttime =poStarttime;
    }
   
}
