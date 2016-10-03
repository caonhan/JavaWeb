package com.serp.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.Customer;


public class CustomerHomeTest {
	static AbstractApplicationContext context;
	static CustomerHome cusDao;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		cusDao= (CustomerHome) context.getBean("customerHome");
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	
	}
/**
 * 
 */
	@Test
	public void testGetAllCustomer() {
		assertEquals(true, cusDao.getAllCustomer().size()>=0);
	}

	@Test
	public void testPersist() {
		Customer cus = new Customer();
		cus.setCustomerId(5);
		cus.setAddress("Hi Tech park");
		cus.setAssignee("Nguyen Minh Trung");
		cus.setCompanyName("FPT software");
		cus.setCreatedBy("admin");
		cus.setCreatedDate(new Date());
		cus.setDescription("This is a test persist");
		cus.setEmail("trungnm@foft.com");
		cus.setFax("2222222");
		cus.setMobilePhone("0933234561");
		cus.setModifiedBy(null);
		cus.setTelephone("086533333");
		cus.setWebsite("fsoft.com.vn");
		cusDao.persist(cus);
		assertNotNull(cusDao.findById(5));
	}
	/**
	 * This function is used to test persist() method, expected having RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testPersistException() {
		Customer cus= null;
		cusDao.persist(cus);
	}


	@Test
	public void testAttachDirty() {
		Customer cus = cusDao.findById(1);
		cus.setDescription("this is test");
		cusDao.attachDirty(cus);
		assertEquals(cusDao.findById(1).getDescription().equals("this is test"), true);
		
	}

	/**
	 * This function is used to test attachDirty() method, expected having RuntimeException
	 */
	@Test(expected= RuntimeException.class)
	public void testAttachDirtyException() {
		Customer cus = null;
		cusDao.attachDirty(cus);
	}
	
	@Test
	public void testDelete() {
		cusDao.delete(cusDao.findById(1));
		assertEquals(cusDao.findById(1), null);
	
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
		cusDao.delete(null);
	}

	@Test
	public void testFindById() {
		assertNotNull(cusDao.findById(1));
		assertNull(cusDao.findById(334));
	}
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		//assertNull(cusDao.findById(id));
	}
}
