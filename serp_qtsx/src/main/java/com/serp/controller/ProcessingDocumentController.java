package com.serp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.serp.entity.LimitInventoryEntity_Dat;
import com.serp.entity.ProcessingDocumentEntity;
import com.serp.entity.ProcessingTechnologyEntity_Dat;
import com.serp.entity.ProductionOrderEntity;
import com.serp.model.ProcessingDocument;
import com.serp.model.ProductionOrder;
import com.serp.service.ProcessingDocumentServiceImpl;
import com.serp.service.ProductionOrderService;


/**
 * @author VIETDAT
 */

@Controller
@RequestMapping("/")
public class ProcessingDocumentController {
    
    String currentUser="";
    String fT_CreatePd = "FT10";
    String tttcn = "FT12";
    String nvqc = "FT11";
    
    private static final Log log = LogFactory.getLog(ProcessingDocumentController.class);
    
    
    @Autowired
    ProcessingDocumentServiceImpl service;
    
    @Autowired
    ProductionOrderService proService;
    
    /**
     * get view processing-document-create
     * @param response
     * @return
     */
    @RequestMapping(value = {"processing-document-create" }, method = RequestMethod.GET)
    public ModelAndView tengicungdc(HttpServletResponse response){
        try{
            response.setContentType("text/html");
            return new ModelAndView("processingdocument/processing_document");
        }catch(Exception e){
            log.error(String.format("get 'create' processing document create %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * get view processing-document-viewall
     * @param response
     * @return
     */
    @RequestMapping(value = {"processing-document-viewall" }, method = RequestMethod.GET)
    public ModelAndView ViewAll(HttpServletResponse response){
        try{
        response.setContentType("text/html");
        return new ModelAndView("processingdocument/ShowList_Processing_document");
        }catch(Exception e){
            log.error(String.format("get 'view' processing document view all %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
        
    }
    
    /**
     * get all processing document 
     * @return list of processing document
     */
    @RequestMapping(value = "processing-document/list", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllProcessingDocument() {
        log.info(String.format("get all id processing document in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            List<ProcessingDocumentEntity> ls = service.getAllProcessingdocument();
            result.put("status", "ok");
            result.put("list", ls);
            log.debug(String.format("get all processing document success!"));
            return result;
        }catch(Exception e){
            log.error(String.format("get all processing document %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    } 
    
    
    /**
     * get all id of proccessing document
     * @param
     * @return : list of processing document (json)
     */
    @RequestMapping(value = "processing-document/list_id_production_order", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllIdProductionOrder() {
        log.info(String.format("get all id production_order in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            List<ProductionOrderEntity> ls = service.getIdProductionOrder();
            result.put("status", "ok");
            result.put("list", ls);
            log.debug(String.format("get all id of production_order success!"));
            return result;
        }catch(Exception e){
            log.error(String.format("get all id of production_order %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * This function used get all id of limit inventory note
     * @param 
     * @return list id of limit inventory note (json)
     */
    @RequestMapping(value = "processing-document/list_id_limit_inventory", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllIdLimitInventory() {
        log.info(String.format("get all id limit_inventory in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            List<LimitInventoryEntity_Dat> ls = service.getIdLimitInventory();
            result.put("status", "ok");
            result.put("list", ls);
            log.debug(String.format("get all id of limit inventory success!"));
            return result;
        }catch(Exception e){
            log.error(String.format("get all id of limit inventory note %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * This function used to get all id of processing technology 
     * @return
     */
    @RequestMapping(value = "processing-document/list_id_processing_technology", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllIdProcessingTechnology() {
        log.info(String.format("get all id processing technology in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            List<ProcessingTechnologyEntity_Dat> ls = service.getAllIdProcessingTechnology();
            result.put("status", "ok");
            result.put("list", ls);
            log.debug(String.format("get all processing technology success!"));
            return result;
        }catch(Exception e){
            log.error(String.format("get all processing technology %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * This status is used to delete a processing document
     * @param processingdocumentId
     * @return status, deleteStatus as json
     */
    @RequestMapping(value = "/processingdocument/delete/{processingDocumentId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deleteProcessingDocument(@PathVariable int processingDocumentId) {
        log.info(String.format("delete a processing document with param 'processingDocumentId' in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", "ok");
            result.put("deleteStatus", service.deleteProcessingDocument(processingDocumentId));
            return result;
            
        }catch(Exception e){
            log.error(String.format("delete processing document %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * This function is used to add new processing document into database, after that return addStatus as json format
     * @param processing document
     * @return status, addStatus as json format 
     */
    @ResponseBody
    @RequestMapping(value = "/processing-document/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> addProcessingDocument(@RequestBody ProcessingDocumentEntity pd) {
        log.info(String.format("add new processing document in class: %s", getClass()));
        try{
            log.debug("adding new processing document into DB and return addStatus as json format");
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", "ok");
            currentUser= getPrincipal();
            
            String userId= currentUser;
            
            result.put("addStatus", service.addProcessingDocument(pd, userId));
            log.debug("add processing document successful");
            return result;
        }catch(Exception e){
            log.error(String.format("add new processing document %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    

    /**
     * This function return userId of the current login user
     * @return userid
     */
    private String getPrincipal(){
        log.info(String.format("getPrincipal in class: %s", getClass()));
        try{
            log.debug("get current login user and return it's username");
            String userName = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     
            if (principal instanceof UserDetails) {
                userName = ((UserDetails)principal).getUsername();
            } else {
                userName = principal.toString();
            }
            System.out.println(userName);
            log.debug("getPrincipal successfully");
            return userName;
        }catch(Exception e){
            log.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    
    /**
     * This function get a processing document when know id
     * @param :  processingDocumentId
     * @return : data of processing document (json)
     */
    @RequestMapping(value = "processing-document/get_a_processing_document/{processingDocumentId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAProcessingDocument(@PathVariable int processingDocumentId) {
        log.info(String.format("getPrincipal in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            ProcessingDocument ls = service.findById(processingDocumentId);
            
            ProcessingDocumentEntity pdEn = new ProcessingDocumentEntity();
            pdEn.setDate(ls.getCreatedDate());
            pdEn.setLimitInventory(ls.getLimitInventory());
            pdEn.setOperationTraceNote(ls.getOperationTraceNote());
            pdEn.setOrders(ls.getOrders());
            pdEn.setPDName(ls.getName());
            pdEn.setProcessingTechnology(ls.getProcessingTechnology());
            pdEn.setProgramNote(ls.getProgramNote());
            
            result.put("status", "ok");
            result.put("list", pdEn);
            return result;
        }catch(Exception e){
            log.error(String.format("get a processing document in class: %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * This function get a production order when know id
     * @param :  processingDocumentId
     * @return : data of processing document (json)
     */
    @RequestMapping(value = "processing-document/get_a_production_order/{productionOderId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAProductionOrder(@PathVariable int productionOderId) {
        log.info(String.format("getPrincipal in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            ProductionOrder ls = proService.findById(productionOderId);
            
            ProductionOrderEntity pdEn = new ProductionOrderEntity();
            pdEn.setPOId(ls.getPoId());
            pdEn.setOrders(ls.getOrders().getOrderId());
            pdEn.setPoContent(ls.getPoContent());
            pdEn.setPoFinishtime(ls.getPoFinishtime());
            pdEn.setPoProcessTechnology(ls.getPoProcessTechnology());
            pdEn.setPoQuantity(ls.getPoQuantity());
            pdEn.setPoStatus(ls.getPoStatus());
            pdEn.setPoTimelength(ls.getPoTimelength());
            pdEn.setPoUnit(ls.getPoUnit());
            pdEn.setUserByPoApprovedBy(ls.getUserByPoApprovedBy().getName());
            pdEn.setUserByPoFactoryManager(ls.getUserByPoFactoryManager().getName());
            pdEn.setPoStarttime(ls.getPoStarttime());
            
            result.put("status", "ok");
            result.put("list", pdEn);
            return result;
        }catch(Exception e){
            log.error(String.format("get a processing document in class: %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }

    
    /**
     * This function get a processing document when know id
     * @param :  processingDocumentId
     * @return : data of processing document (json)
     */
    @RequestMapping(value = "processing-document/check-role", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkRole() {
        log.info(String.format("check role in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            //get roleId
            currentUser= getPrincipal();
            String userId = currentUser;
            boolean re = service.checkRole(userId, fT_CreatePd);
            result.put("status", "ok");
            result.put("list", re);
            log.debug("check role in class success");
            return result;
        }catch(Exception e){
            log.error(String.format("check role in class: %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    /**
     * This function check role view all
     * @return : true - false
     */
    @RequestMapping(value = "processing-document/check-role-viewall", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkRoleViewAll() {
        log.info(String.format("check role in class: %s", getClass()));
        try{
            Map<String, Object> result = new HashMap<String, Object>();
            //get roleId
            currentUser= getPrincipal();
            String userId = currentUser;
            boolean re1 = service.checkRole(userId, fT_CreatePd);
            boolean re2 = service.checkRole(userId, tttcn);
            boolean re3 = service.checkRole(userId, nvqc);
            
            boolean re = (re1|| re2||re3);
            result.put("status", "ok");
            result.put("list", re);
            log.debug("check role in class success");
            return result;
        }catch(Exception e){
            log.error(String.format("check role in class: %s has error: %s", getClass(), e.getMessage()));
            throw e;
        }
    }
    
    
}
