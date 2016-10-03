package com.serp.testRun;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.dao.ProcessingProgramDetailDAO;
import com.serp.dao.ProcessingProgramDAO;

public class ProcessingProgramDetailDAOImplTest {

	static AbstractApplicationContext context;
	static ProcessingProgramDetailDAO processingProgramDetailDAO;
	static ProcessingProgramDAO processingProgramDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		processingProgramDetailDAO = (ProcessingProgramDetailDAO) context.getBean("processingProgramDetailDAO");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	/** The number of listProcessingProgramDetail is compared with 0*/
	@Test(expected = RuntimeException.class)
	public void testListProcessingProgramDetailForTry() {
		processingProgramDetailDAO.listProcessingProgramDetail(0000012136541);
	}
	
	@Test
	public void testListProcessingProgramDetailForCatch() {
	}
	
	@Test
	public void testAddForTry() {
		ProcessingProgram pp = processingProgramDAO.findProcessingProgramById(1);
		ProcessingProgramDetail ppd = new ProcessingProgramDetail();
		ppd.setProcessingProgram(pp);
		processingProgramDetailDAO.add(ppd);
	}
	
	/** The ProcessingProgramDetail is added by error*/
	@Test
	public void testAddForCarch() {
		processingProgramDetailDAO.add(null);
	}
	
	
	/** The result of searchProcessingProgramDetail is checked*/
	@Test
	public void testSearchProcessingProgramDetailForTry() {
		if(processingProgramDetailDAO.listProcessingProgramDetail(1).size() != 0)
		{
			assertNotNull(processingProgramDetailDAO.searchProcessingProgramDetail(1));
		}else
		{
			assertNull(processingProgramDetailDAO.searchProcessingProgramDetail(0));
		}	
	}
	
	@Test(expected = RuntimeException.class)
	public void testSearchProcessingProgramDetailForCatch() {
		processingProgramDetailDAO.searchProcessingProgramDetail(null);
	}

	/** The ProcessingProgramDetail is deleted*/
	@Test
	public void testDeleteForTry() {
		ProcessingProgramDetail pp = new ProcessingProgramDetail();
		processingProgramDetailDAO.delete(pp);
	}
	
	/** The ProcessingProgram is deleted error*/
	@Test
	public void testDeleteForCatch() {
		processingProgramDetailDAO.delete(null);
	}
}