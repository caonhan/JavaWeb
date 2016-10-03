package com.serp.entity;

import java.util.Date;

public class ProcessingDocumentEntity {

    private int id;
    private String name;
    private Date date;
    private int limitInventory;
    private String orders;
    private String processingTechnology;
    private String programNote;
    private String operationTraceNote;
    
    
    public int getLimitInventory() {
        return limitInventory;
    }

    public void setLimitInventory(int limitInventory) {
        this.limitInventory = limitInventory;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getProcessingTechnology() {
        return processingTechnology;
    }

    public void setProcessingTechnology(String processingTechnology) {
        this.processingTechnology = processingTechnology;
    }

    public String getOperationTraceNote() {
        return operationTraceNote;
    }

    public void setOperationTraceNote(String operationTraceNote) {
        this.operationTraceNote = operationTraceNote;
    }

    public String getProgramNote() {
        return programNote;
    }

    public void setProgramNote(String programNote) {
        this.programNote = programNote;
    }

    public int getPDId() {
        return id;
    }

    public void setPDId(int PDId) {
        this.id = PDId;
    }
    
    public String getPDName(){
        return name;
    }
    
    public void setPDName(String name){
        this.name = name;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }

    public ProcessingDocumentEntity() {
        super();
    }
    
    public ProcessingDocumentEntity(int id) {
        super();
        this.id = id;
    }
   
    public ProcessingDocumentEntity(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    } 
    
    public ProcessingDocumentEntity(int id, String name, Date date) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
    }
    
    
    public ProcessingDocumentEntity(int id, String name, Date date, String orders, String processingTechnologyNote, 
            String traceNote, String programNote, int limitInventoryNote) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
        this.orders = orders;
        this.processingTechnology = processingTechnologyNote;
        this.operationTraceNote = traceNote;
        this.programNote = programNote;
        this.limitInventory = limitInventoryNote;
    }
    
    
    
        
    
}
