/**
 * 
 */
package com.serp.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.Estimate;

/**
 * @author vuong-bt
 *
 */
public class EstimateDAOImplTest {
    static AbstractApplicationContext context;
    static EstimateDAO estimateDAO;
    @InjectMocks
    EstimateDAOImpl eD;
    @Mock
    SessionFactory sessionFactory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	context = new AnnotationConfigApplicationContext(AppConfig.class);
	estimateDAO = (EstimateDAO) context.getBean("estimateDAO");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	context.close();
    }

    /**
     * Test method for
     * {@link com.serp.dao.EstimateDAOImpl#persist(com.serp.model.Estimate)}.
     */
    @Test
    public void testPersist() {
	int num1 = estimateDAO.list().size();
	Estimate estimate = estimateDAO.findById(38);
	estimate.setEsId(null);
	estimate.setEstimateDetail(null);
	estimateDAO.persist(estimate);
	assertEquals(num1 + 1, estimateDAO.list().size());
    }

    @Test(expected = Exception.class)
    public void testPersistException() {
	estimateDAO.persist(null);
    }

    /**
     * Test method for
     * {@link com.serp.dao.EstimateDAOImpl#saveOrUpdate(com.serp.model.Estimate)}
     * .
     */
    @Test
    public void testSaveOrUpdate() {
	Estimate estimate = estimateDAO.findById(38);
	estimate.setEsApproveContent("changed");
	estimateDAO.saveOrUpdate(estimate);
	assertEquals("changed", estimateDAO.findById(38).getEsApproveContent());
    }

    @Test(expected = Exception.class)
    public void testSaveOrUpdateNull() {
	estimateDAO.saveOrUpdate(null);
    }

    /**
     * Test method for
     * {@link com.serp.dao.EstimateDAOImpl#delete(com.serp.model.Estimate)}.
     */
    @Test
    public void testDelete() {
	int num1 = estimateDAO.list().size();
	estimateDAO.delete(estimateDAO.findById(39));
	assertEquals(num1 - 1, estimateDAO.list().size());
    }

    @Test(expected = Exception.class)
    public void testDeleteException() {
	estimateDAO.delete(estimateDAO.findById(3999));
    }

    /**
     * Test method for
     * {@link com.serp.dao.EstimateDAOImpl#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
	assertNotNull(estimateDAO.findById(38));
    }

    @Test
    public void testFindByIdNull() {
	assertNull(estimateDAO.findById(389));
    }
    @Test(expected = Exception.class)
    public void testFindByIdException() {
	estimateDAO.findById(null);
    }
    /**
     * Test method for {@link com.serp.dao.EstimateDAOImpl#list()}.
     */
    @Test
    public void testList() {
	assertEquals(1, estimateDAO.list().size());
    }
    @Test(expected = Exception.class)
    public void testListException() {
	MockitoAnnotations.initMocks(this);
	sessionFactory = mock(SessionFactory.class);
	when(sessionFactory.getCurrentSession()).thenReturn(null);
	eD.list();
    }

}
