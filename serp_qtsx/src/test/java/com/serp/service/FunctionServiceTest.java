package com.serp.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;

public class FunctionServiceTest {

	static AbstractApplicationContext context;
	static FunctionService ser;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (FunctionService) context.getBean("functionService");
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
	 * This function is used to test findById() method, expected run successfully.
	 */
	@Test
	public void testFindById() {
		assertNotNull(ser.findById("FT1"));
	}

	/**
	 * This function is used to test findById() method, expected Exception.
	 */
	@Test(expected= Exception.class)
	public void testFindByIdException() {
		ser.findById(null);
	}
}
