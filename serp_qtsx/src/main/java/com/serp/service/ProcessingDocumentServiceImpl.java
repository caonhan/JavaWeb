package com.serp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.LimitInventoryDAO;
import com.serp.dao.ProcessingDocumentDAO;
import com.serp.dao.ProcessingTechnologyHome;
import com.serp.dao.ProductionOrderHome;
import com.serp.dao.UserDAO;
import com.serp.dao.UserLoginDao;
import com.serp.entity.FunctionEntity;
import com.serp.entity.LimitInventoryEntity_Dat;
import com.serp.entity.ProcessingDocumentEntity;
import com.serp.entity.ProcessingTechnologyEntity_Dat;
import com.serp.entity.ProductionOrderEntity;
import com.serp.model.LimitInventory;
import com.serp.model.ProcessingDocument;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProductionOrder;
import com.serp.model.Role;
import com.serp.model.User;

@Service("processingDocumentServiceImpl")
public class ProcessingDocumentServiceImpl {
    
    private static final Log log = LogFactory.getLog(ProcessingDocumentServiceImpl.class);
    
    @Autowired
    ProcessingDocumentDAO dao ;
    
    @Autowired
    ProductionOrderHome dao_production_order;
    
    @Autowired
    LimitInventoryDAO dao_limitInventory;
    
    @Autowired
    ProcessingTechnologyHome daoProcessingTechnology;
    
    @Autowired
    UserLoginDao userDao;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    UserDAO user_Dao;
    
    /**
     * get a element use id
     * @param id
     * @return1
     */
    public ProcessingDocument findById(Integer id){
        try{
            return dao.findById(id);
        }catch(Exception e){
            log.error(String.format("find by id in class: %s has error: %s", getClass(), e.getMessage()));
            return null;
        }
    }
    
    
    /**
     * Get id of limit inventory
     * @return List id of production order
     */
    public List<ProductionOrderEntity> getIdProductionOrder(){
        log.info(String.format("get id of limit inventory in class: %s", getClass()));
        try{
            List<ProductionOrder> lst = dao_production_order.getAllProductionOrders();
            ProductionOrderEntity productionOrderEntity;
            
            List<ProductionOrderEntity> lstPOEntity = new ArrayList<ProductionOrderEntity>();
            for (ProductionOrder or : lst) {
                productionOrderEntity = new ProductionOrderEntity();
                productionOrderEntity.setPOId(or.getPoId());
                lstPOEntity.add(productionOrderEntity);
            }
            log.debug("get id of limit inventory in database successfully");
            return lstPOEntity;
        }catch(Exception e){
            log.error(String.format("get id of limit inventory in class: %s has error: %s", getClass(), e.getMessage()));
            return null;
        }
    }
    
    /**
     * get id of limit inventory
     * @return List id of production order
     */
    public List<LimitInventoryEntity_Dat> getIdLimitInventory(){
        log.info(String.format("get id of limit inventory in class: %s", getClass()));
        try{
            List<LimitInventory> lst = dao_limitInventory.getAllLimitInventories();
            LimitInventoryEntity_Dat limitInventoryEntity;
            
            List<LimitInventoryEntity_Dat> lstLIEntity = new ArrayList<LimitInventoryEntity_Dat>();
            for (LimitInventory li : lst) {
                limitInventoryEntity = new LimitInventoryEntity_Dat();
                limitInventoryEntity.setLimitInventoryId(li.getLimitInventoryId());
                lstLIEntity.add(limitInventoryEntity);
            }
            log.debug("get id of limit inventory in database successfully");
            return lstLIEntity;
        } catch(Exception e){
            log.error(String.format("get id of limit inventory in class: %s has error: %s", getClass(), e.getMessage()));
            return null;
        }
    }
    
    
    /**
     * Get all processing document
     * @return Id, name, date of Processing document
     */
    public List<ProcessingDocumentEntity> getAllProcessingdocument(){
        log.info(String.format("get all processing document in class: %s", getClass()));
        try{
            List<ProcessingDocument> lst = dao.getAllProcessingdocument();
            
            ProcessingDocumentEntity pdEn;
            
            List<ProcessingDocumentEntity> lstPDEntity = new ArrayList<ProcessingDocumentEntity>();
            for (ProcessingDocument or : lst) {
                pdEn = new ProcessingDocumentEntity();
                pdEn.setPDId(or.getId());
                pdEn.setDate(or.getCreatedDate());
                pdEn.setPDName(or.getName());
                pdEn.setLimitInventory(or.getLimitInventory());
                pdEn.setOperationTraceNote(or.getOperationTraceNote());
                pdEn.setOrders(or.getOrders());
                pdEn.setProgramNote(or.getProgramNote());
                pdEn.setProcessingTechnology(or.getProcessingTechnology());
                lstPDEntity.add(pdEn);
            }
            log.debug("get all of processing document in database successfully");
            return lstPDEntity;
        }catch(Exception e){
            log.error(String.format("get all of processing document in class: %s has error: %s", getClass(), e.getMessage()));
            return null;
        }
        
    }
    
    /**
     * This function is used to get all id of processing technology
     * @return List id of processing technology
     */
    public List<ProcessingTechnologyEntity_Dat> getAllIdProcessingTechnology(){
        log.info(String.format("get all processing technology in class: %s", getClass()));
        try{
            List<ProcessingTechnology> lst = daoProcessingTechnology.getAllIdProcessingTechnology();
            ProcessingTechnologyEntity_Dat ptEn;
            
            List<ProcessingTechnologyEntity_Dat> lstPtEn = new ArrayList<ProcessingTechnologyEntity_Dat>();
            for (ProcessingTechnology li : lst) {
                ptEn = new ProcessingTechnologyEntity_Dat();
                ptEn.setId(li.getPtId());
                lstPtEn.add(ptEn);
            }
            log.debug("get all id of processing technology in database successfully");
            return lstPtEn;
        }catch(Exception e){
            log.error(String.format("get all id of processing technology in class: %s has error: %s", getClass(), e.getMessage()));
            return null;
        }     
    }
    
    
    /**
     * This function is used to delete a processing document in database.
     * @param processingdocumentID
     * @return true if delete successfully, false if cant
     */
    public boolean deleteProcessingDocument(Integer processingDocumentId){
        ProcessingDocument pd= dao.findById(processingDocumentId);
        try{
            dao.delete(pd);
            log.debug("delete a processing document in database successfully");
            return true;
        }catch(Exception e){
            log.error(String.format("delete processing document in class: %s has error: %s", getClass(), e.getMessage()));
            return false;
        }
    }
    
    
    
    
    /**
     * This function is used to add new processing document into database
     * @param pdEn
     * @return true if add successfully, false if have exception
     */  
    public boolean addProcessingDocument(ProcessingDocumentEntity pdEn, String userId){
        log.info(String.format("add processing document in class: %s", getClass()));
        try{
            ProcessingDocument pd= new ProcessingDocument();
            User instance =  user_Dao.findById(userId);
            
            String userName = instance.getName(); 
            
            int id = (int) (new Date().getTime()/1000);
            pd.setId(id);
            pd.setName(userName);
            pd.setCreatedDate(new Date());
            pd.setLimitInventory(pdEn.getLimitInventory());
            pd.setProcessingTechnology(pdEn.getProcessingTechnology());
            pd.setOperationTraceNote(pdEn.getOperationTraceNote());
            pd.setProgramNote(pdEn.getProgramNote());
            pd.setOrders(pdEn.getOrders());
            
            dao.persist(pd);
            log.debug("add new processing document into database successfully");
            return true;
        }catch(Exception e){
            log.error(String.format("add processing document in class: %s has error: %s", getClass(), e.getMessage()));
            System.err.println(String.format("add processing document in class: %s has error: %s", getClass(), e.getMessage()));
            return false;
        }
    }
    
    /**
     * This function check functionIf know roleId
     * @param userId
     * @param functionId
     * @return
     */
    public boolean checkRole(String userId, String functionId){
        log.info(String.format("Check functionId in class: %s", getClass()));
        try{          
            User instance =  user_Dao.findById(userId);
            Role role = instance.getRole();
            String roleId = role.getRoleId(); 
            
            List<FunctionEntity> lst= new ArrayList<FunctionEntity>(); 
            lst = roleService.listAllFuncOfRoleById(roleId);
            for (FunctionEntity functionEntity : lst) {
                if(functionEntity.getFunctionId().equals(functionId)){
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            log.error(String.format("Check functionId in class: %s has error: %s", getClass(), e.getMessage()));
            return false;
        }        
    }
    
    
}
