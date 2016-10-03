/**
 * 
 */
package com.serp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import com.serp.dao.EstimateDAOImpl;
import com.serp.model.Estimate;

/**
 * @author vuong-bt
 *
 */
public class EstimateServiceImplTest {
    static AbstractApplicationContext context;
    static EstimateService estimateService;
    static UserLoginService userService;
    static StatusService statusService;
    @InjectMocks
    EstimateServiceImpl eS;
    @InjectMocks
    EstimateDAOImpl eD;
    @Mock
    SessionFactory sessionFactory;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	context = new AnnotationConfigApplicationContext(AppConfig.class);
	estimateService = (EstimateService) context.getBean("estimateService");
	userService = (UserLoginService) context.getBean("UserLoginService");
	statusService = (StatusService) context.getBean("statusService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	context.close();
    }

    /**
     * Test method for
     * {@link com.serp.service.EstimateServiceImpl#addEstimate(com.serp.model.Estimate)}
     * .
     */
    @Test
    public void testAddEstimate() {
	Estimate estimate = estimateService.list().get(0);
	estimate.setEsId(null);
	estimate.setEstimateDetail(null);
	assertTrue(estimateService.addEstimate(estimate));

    }

    @Test
    public void testAddEstimateException() {
	Estimate estimate = estimateService.list().get(0);
	assertFalse(estimateService.addEstimate(estimate));
    }

    /**
     * Test method for
     * {@link com.serp.service.EstimateServiceImpl#saveOrUpdate(com.serp.model.Estimate)}
     * .
     */
    @Test
    public void testSaveOrUpdate() {
	Estimate estimate = estimateService.list().get(0);
	estimate.setStatus(statusService.findById(2));
	estimateService.saveOrUpdate(estimate);
	estimate = estimateService.list().get(0);
	assertTrue(estimate.getStatus().getStatusId() == 2);
    }

    @Test(expected = Exception.class)
    public void testSaveOrUpdateException() {
	estimateService.saveOrUpdate(null);
    }

    /**
     * Test method for
     * {@link com.serp.service.EstimateServiceImpl#delete(com.serp.model.Estimate)}
     * .
     */
    @Test
    public void testDelete() {
	int num1 = estimateService.list().size();
	estimateService.delete(estimateService.findById(39));
	int num2 = estimateService.list().size();
	assertEquals(1, num1 - num2);
    }

    @Test(expected = Exception.class)
    public void testDeleteNull() {
	estimateService.delete(estimateService.findById(99));
    }

    /**
     * Test method for
     * {@link com.serp.service.EstimateServiceImpl#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
	Estimate estimate = estimateService.findById(38);
	assertEquals(38,(int)estimate.getEsId());
    }

    @Test
    public void testFindByIdNull() {
	Estimate estimate = estimateService.findById(null);
	assertNull(estimate);
    }

    /**
     * Test method for {@link com.serp.service.EstimateServiceImpl#list()}.
     */
    @Test
    public void testList() {
	assertEquals(1,estimateService.list().size());
    }
    
    @Test
    public void testListException() {
	MockitoAnnotations.initMocks(this);
	sessionFactory = mock(SessionFactory.class);
	when(sessionFactory.getCurrentSession()).thenReturn(null);
	assertNull(eS.list());
    }

    /**
     * Test method for
     * {@link com.serp.service.EstimateServiceImpl#hasEditRole(com.serp.model.User)}
     * .
     */
    @Test
    public void testHasEditRole() {
	assertTrue(estimateService.hasEditRole(userService.findById("tcn1")));
    }

    @Test
    public void testHasEditRoleFalse() {
	assertFalse(estimateService.hasEditRole(userService.findById("txuong1")));
    }

    @Test
    public void testHasEditRoleNull() {
	assertFalse(estimateService.hasEditRole(null));
    }

    /**
     * Test method for
     * {@link com.serp.service.EstimateServiceImpl#hasApproveRole(com.serp.model.User)}
     * .
     */
    @Test
    public void testHasApproveRole() {
	assertTrue(estimateService.hasApproveRole(userService.findById("txuong1")));
    }

    @Test
    public void testHasApproveRoleFalse() {
	assertFalse(estimateService.hasApproveRole(userService.findById("tcn1")));
    }

    @Test
    public void testHasApproveRoleNull() {
	assertFalse(estimateService.hasApproveRole(null));
    }

}
