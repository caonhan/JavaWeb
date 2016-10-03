package com.serp.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.entity.ProcessingDocumentEntity;

/**
 * 
 * @author VIETDAT
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessingDocumentTest {
    static AbstractApplicationContext context;
    static ProcessingDocumentServiceImpl ser;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        ser= (ProcessingDocumentServiceImpl) context.getBean("processingDocumentServiceImpl");
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }
    
    /**
     * Test get id of production order
     */
    @Test
    public void testGetIdProductionOrder(){
        assertNotNull(ser.getIdProductionOrder());
    }
    
    /**
     * Test get is of Limit inventory
     */
    @Test
    public void testGetIdLimitInventory(){
        assertNotNull(ser.getIdLimitInventory());
    }
    
   
    /**
     * Test get all processing document
     */
    @Test
    public void testGetAllProcessingdocument(){
        assertNotNull(ser.getAllProcessingdocument());
    }

    /**
     * Test get all processing technology
     */
    @Test
    public void testGetAllIdProcessingTechnology(){
        assertNotNull(ser.getAllIdProcessingTechnology());
    }
   
    /**
     * Test delete processing document
     * -add new processing document
     * - delete it
     * - check null
     */
    @Test
    public void testDeleteProcessingDocument(){
        ProcessingDocumentEntity pd= new ProcessingDocumentEntity();
        int id1 = (int) (new Date().getTime()/1000);
        
        pd.setPDId(id1);
        pd.setPDName("Mark");
        pd.setDate(new Date());
        pd.setLimitInventory(3);
        pd.setProcessingTechnology("1");
        pd.setOperationTraceNote("TN001");
        pd.setProgramNote("PN002");
        pd.setOrders("1");
        
        ser.addProcessingDocument(pd, "nvcn");
        
        ser.deleteProcessingDocument(id1);
    }
    
    
    
    /**
     * Test function AddProcessingDocument
     * input: pd
     */
    @Test
    public void testAddProcessingDocument(){
        ProcessingDocumentEntity pd= new ProcessingDocumentEntity();
        int id1 = (int) (new Date().getTime()/1000);
        
        pd.setPDId(id1);
        pd.setPDName("Nhân viên công nghệ");
        pd.setDate(new Date());
        pd.setLimitInventory(3);
        pd.setProcessingTechnology("1");
        pd.setOperationTraceNote("TN001");
        pd.setProgramNote("PN002");
        pd.setOrders("1");
        
        ser.addProcessingDocument(pd, "nvcn");
        
        assertNotNull(ser);
    }
  
    /**
     * Test function findbyid
     */
    @Test
    public void testFindById(){
        assertNotNull(ser.findById(1462112545));
    }
    
    /**
     * Test function check role
     * - nvcn vs FT10 -- true
     * - nvcn vs FT9 -- false
     */
    @Test
    public void testCheckRole(){
        assertTrue(ser.checkRole("nvcn", "FT10"));
        assertFalse(ser.checkRole("nvcn", "FT9"));
    }
    
    /**
     * Test exception function findById of ProcessingDocumentDao
     */
    @Test(expected=Exception.class)
    public void ztestFindByIdException() {
        ser.findById(null);
    }
   
    @Test(expected=Exception.class)
    public void ztestCheckRoleException(){
        ser.checkRole("nvcn", null);
    }
    
    @Test(expected=Exception.class)
    public void ztestDeleteProcessingDocumentException(){
        ser.deleteProcessingDocument(null);
    }
    
    @Test(expected=Exception.class)
    public void zztestAddProcessingDocumentException(){
        ProcessingDocumentEntity pd= new ProcessingDocumentEntity();
        ser.addProcessingDocument(pd, null);
    }
}
