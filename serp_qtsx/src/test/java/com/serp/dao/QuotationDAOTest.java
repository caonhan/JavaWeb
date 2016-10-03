package com.serp.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.entity.QuotationEntity;
import com.serp.model.Estimate;
import com.serp.model.Quotation;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuotationDAOTest {
	static AbstractApplicationContext context;
	static QuotationDAO quoSer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		quoSer= (QuotationDAO) context.getBean("quotationDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAllListQuotation() {
		assertNotNull(quoSer.allListQuotation());
	}
	
	@Test
	public void testUpdateStatus() {
		Quotation quo=new Quotation();
		quo.setQuotationId("1");
		quo.setStatus(1);
		
		boolean flag=quoSer.updateStatus(quo);
		assertEquals(true, flag);
	}
	@Test(expected=RuntimeException.class)
	public void testUpdateStatusException() {
		Quotation quo=new Quotation();
		quo.setQuotationId("7");
		quoSer.updateStatus(quo);		
	}
	
	@Test
	public void testSaveOrUpdate() {
		QuotationEntity quo=new QuotationEntity();
		quo.setQuotationId("1");
		quo.setNumOfDaysToComplete(5);
		quo.setNumOfValidityDays("5");
		quo.setPaymentMethod1("cast");
		quo.setPaymentMethod2("cast");
		quo.setStatus(3);
		quo.setUserId("txuong1");		
		quo.setPublishDate(new Date());
		quo.setAmount(3000.0);
		quo.setVat(300.0);
		quo.setTotalAmount(3300.0);				
		quo.setEstimate(38);
		
		boolean flag=quoSer.saveOrUpdate(quo);
		
		assertEquals(true,flag);
	}
	@Test(expected=RuntimeException.class)
	public void testSaveOrUpdateException() {
		Quotation quo=new Quotation();
		quo.setQuotationId("6");
		quo.setNumOfDaysToComplete(5);
		quo.setNumOfValidityDays("5");
		
		boolean expect=quoSer.updateStatus(quo);
		assertEquals(expect, false);
	}
	
	@Test
	public void testPersist() {
		QuotationEntity quoE=new QuotationEntity();
		quoE.setQuotationId("2");
		quoE.setEstimate(38);
		boolean flag=quoSer.persist(quoE);		
		assertEquals(true, flag);
	}
	@Test(expected=RuntimeException.class)
	public void testPersistException() {
		QuotationEntity quoE= null;
		quoSer.persist(quoE);
		//assertNotNull(quoSer.findByEsId("1"));
	}	
	
	@Test
	public void testFindByEsId() {
		assertNotNull(quoSer.findByEsId("1"));
	}
	@Test(expected=RuntimeException.class)
	public void testFindByEsIdException() {		
		assertNotNull(quoSer.findByEsId(""));		
	}
	
	@Test
	public void testFindByEstimateId() {
		assertNotNull(quoSer.findByEstimateId(3));
	}
	@Test(expected=RuntimeException.class)
	public void testFindByEstimateIdException() {		
		assertNotNull(quoSer.findByEstimateId(3));
	}
	
	@Test
	public void testUpdateContent() {
		Estimate es=new Estimate();
		es.setEsId(38);
		es.setEsApproveContent("Yes");
		boolean expect=quoSer.updateContent(es);
		assertEquals(expect, true);
	}
	@Test(expected=RuntimeException.class)
	public void testUpdateContentException() {		
		Estimate es=null;
		quoSer.updateContent(es);
	}
}
