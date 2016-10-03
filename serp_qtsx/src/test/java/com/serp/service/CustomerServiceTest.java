package com.serp.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import com.serp.configuration.AppConfig;
import com.serp.entity.CustomerEntity;



public class CustomerServiceTest {
	static AbstractApplicationContext context;
	static CustomerService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		service= (CustomerService) context.getBean("customerService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	context.close();
	}

	@Test
	public void testGetAllCustomer() {
		assertEquals(true, service.getAllCustomer().size()>=0);
	}

	@Test
	public void testDeleteCustomer() {
		assertEquals(true, service.deleteCustomer(2));
	}

	@Test
	public void testAddCustomer() {
		CustomerEntity cn= new CustomerEntity();
		cn.setCustomerId(1);
	
		cn.setAddress("Hi Tech park");
		cn.setAssignee("Nguyen Minh Trung");
		cn.setCompanyName("FPT software");
		cn.setCreatedBy("admin");
		cn.setCreatedDate(new Date());
		cn.setDescription("This is a test persist");
		cn.setEmail("trungnm@foft.com");
		cn.setFax("2222222");
		cn.setMobilePhone("0933234561");
		cn.setModified_by(null);
		cn.setTelePhone("086533333");
		cn.setWebsite("fsoft.com.vn");
		assertEquals(false, service.addCustomer(cn, "admin"));
	}

	@Test
	public void testIsCustomerExistedById() {
		assertEquals(true, service.isCustomerExistedById(1));
		assertEquals(false, service.isCustomerExistedById(23));
	}
	
	@Test
	public void testEditCustomer() {
		assertEquals(true, service.editCustomer(service.findCustomerEntityById(1), "admin"));
	}

	
	@Test
	public void testFindCustomerEntityById() {
		CustomerEntity ce= service.findCustomerEntityById(1);
		assertEquals(true, ce.getCustomerId()==1);
		ce= service.findCustomerEntityById(1);
	}
	

}
