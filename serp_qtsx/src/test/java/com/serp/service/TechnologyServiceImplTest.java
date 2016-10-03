package com.serp.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;

public class TechnologyServiceImplTest {

	static AbstractApplicationContext context;
	static TechnologyService technology;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		technology = (TechnologyService) context.getBean("technologyService");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	@Test
	public void testList() {
		List<ProcessingTechnologyDetail> listTech = technology.list();
		
	}

	@Test
	public void testAdd() {
		ProcessingTechnologyDetail technologyDetail = technology.list().get(0);
		int number = technology.list().size();
		technologyDetail.setId(0);
		technology.add(technologyDetail);
		assertEquals(number +1, technology.list().size());
	}
	@Test
	public void testAddNull() {
		technology.add(null);
	}


	@Test
	public void testUpdate() {
		ProcessingTechnologyDetail technologyDetail = technology.findById(13);
		technologyDetail.setJig("Do Ga");
		technology.update(technologyDetail);
		assertNotEquals(true, technology.findById(13));
	}
	
	@Test
	public void testUpdateNull() {
		technology.update(null);
	}

	@Test(expected=Exception.class)
	public void testFindById() {
		ProcessingTechnologyDetail pt = technology.findById(3);
//		pt.setId(2);
//		technology.findById(2);
		assertEquals(pt, technology.findById(3));
	}
	
	@Test
	public void testFindByIdNull() {
		technology.findById(null);
	}

	@Test
	public void testSave() {
		
	}
	
	@Test
	public void testSaveNull() {
		technology.save(null);
		
	}

	@Test
	public void testListProcessingTechnology() {
		List<ProcessingTechnology> listProcessingTechnology = technology.listProcessingTechnology();
	}
	
	@Test
	public void testListProcessingTechnologyNull() {
		technology.list();
	}

}
