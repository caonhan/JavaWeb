package com.serp.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;

public class FunctionDaoTest {

	static AbstractApplicationContext context;
	static FunctionDao dao;
	
	@Autowired
	FunctionDao dao1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao= (FunctionDao) context.getBean("functionDao");
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

	/**
	 * This function is used to test findById() method, expected find instance case and not find case, run successfully
	 */
	@Test
	public void testFindById() {
		assertNotNull(dao.findById("FT1"));
		assertNull(dao.findById("XX"));
	}

	/**
	 * This function is used to test findById() method, expected RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		//dao1= null;
		dao.findById(null);
	}
	
	/**
	 * This function is used to test findById() method, expected RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testGetAllException() {
		dao1= null;
		dao1.getAllFunction();
	}
}
