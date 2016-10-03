package com.serp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.serp.configuration.AppConfig;
import com.serp.model.ProcessingDocument;

/**
 * 
 * @author VIETDAT
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessingDocumentDaoTest {
    
    static AbstractApplicationContext context;
    static ProcessingDocumentDAO dao;
    static ProcessingDocument pd = new ProcessingDocument();
    static int id = (int) (new Date().getTime()/1000);
    
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        dao= (ProcessingDocumentDAO) context.getBean("processingDocumentDAO");
        pd.setId(id);
        pd.setName("Mark");
        pd.setCreatedDate(new Date());
        pd.setLimitInventory(3);
        pd.setProcessingTechnology("1");
        pd.setOperationTraceNote("TN001");
        pd.setProgramNote("PN002");
        pd.setOrders("1");
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }
    
    /**
     * This function test function persist.
     */
    @Test
    public void testPersist() {
        dao.persist(pd);
        assertNotNull(dao.findById(id));
    }
    
    /**
     * Test persist exception
     */
    @Test(expected=RuntimeException.class)
    public void testPersistException(){
        dao.persist(null);
    }
    
    /**
     * Test function findById of ProcessingDocumentDao
     */
    @Test
    public void testfindById(){
        assertNotNull(dao.findById(1462112545));
    }
    
    
    /**
     * Test exception function findById of ProcessingDocumentDao
     */
    @Test(expected=RuntimeException.class)
    public void testFindByIdException() {
        assertNull(dao.findById(null));
    }
    
    
    /**
     * Test function delete in processing document dao
     */
    @Test
    public void testDelete(){
        dao.delete(pd);
        assertEquals(dao.findById(id), null);
    }
    
    /**
     * Test exception function delete of ProcessingDocumentDao 
     */
    @Test(expected=RuntimeException.class)
    public void testDeleteException(){
        dao.delete(null);
    }
    
    /**
     * Test function getAllProcessingdocument ProcessingDocumentDao
     */
    @Test
    public void testGetAllProcessingDocument(){
        List<ProcessingDocument> lsPd = new ArrayList<ProcessingDocument>();
        
        lsPd = dao.getAllProcessingdocument();
        
        assertEquals(lsPd.size()>0, true);
    }
    
    /**
     * Test exception function getAllProcessingdocument ProcessingDocumentDao
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @Test(expected=Exception.class)
    public void ztestGetAllProcessingDocumentException() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        Class<?> c = dao.getClass();
        Field f = c.getDeclaredField("sessionFactory");
        f.set(dao, null);
        dao.getAllProcessingdocument();
    }
   
    

}
