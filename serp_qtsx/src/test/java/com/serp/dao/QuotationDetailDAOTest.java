package com.serp.dao;

import static org.junit.Assert.*;

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
import com.serp.entity.QuotationDetailEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuotationDetailDAOTest {

	static AbstractApplicationContext context;
	static QuotationDetailDAO quoSer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		quoSer= (QuotationDetailDAO) context.getBean("quotationDetailDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersist() {
		QuotationDetailEntity quoE=new QuotationDetailEntity();
		quoE.setQuotationId("1");
		quoE.setNameOfDetail("Win 11");
		quoE.setQuantity(3);
		quoE.setPrice(30000.0);
		quoE.setAmount(90000.0);
		quoE.setUnit("Sets");
		quoE.setNote("Hehe");
		boolean flag=quoSer.persist(quoE);
		assertEquals(true, flag);
	}
	
	@Test(expected=RuntimeException.class)
	public void testPersistException() {
		QuotationDetailEntity quoE= null;
		quoSer.persist(quoE);		
	}
	
	@Test
	public void testSaveOrUpdate() {
		QuotationDetailEntity quoE=new QuotationDetailEntity();
		quoE.setQuotationDetailsId(36);
		quoE.setQuotationId("1");
		quoE.setNameOfDetail("Win 11");
		quoE.setQuantity(300);
		quoE.setPrice(10000.0);
		quoE.setAmount(3000000.0);
		quoE.setUnit("Set");
		quoE.setNote("Try hard !");
		boolean flag=quoSer.saveOrUpdate(quoE);
		assertEquals(true, flag);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSaveOrUpdateException() {
		QuotationDetailEntity quoE= null;
		quoSer.saveOrUpdate(quoE);		
	}
	@Test
	public void testFindQuoDetailById() {
		QuotationDetailEntity qe=quoSer.findQuoDetailById(2);
		assertNotNull(qe);
	}
	@Test(expected=RuntimeException.class)
	public void testFindQuoDetailByIdException() {
		assertNotNull(quoSer.findQuoDetailById(30));
	}
	@Test
	public void testDelete() {
		int idQuo=1;
		boolean flag=quoSer.delete(idQuo);
		assertEquals(true, flag);
	}

	@Test
	public void testAllListQuotationDetail() {
		assertNotNull(quoSer.allListQuotationDetail());
	}

	
}
