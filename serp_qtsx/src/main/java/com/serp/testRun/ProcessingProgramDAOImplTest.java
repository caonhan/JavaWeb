package com.serp.testRun;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.dao.ProcessingProgramDAO;
import com.serp.model.ProcessingProgram;

public class ProcessingProgramDAOImplTest {
	
	static AbstractApplicationContext context;
	static ProcessingProgramDAO processingProgramDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		processingProgramDAO = (ProcessingProgramDAO) context.getBean("processingProgramDAO");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	/** The listProcessingProgram is compared with null*/
	@Test
	public void testListProcessingProgramForTry() {
		assertNotNull(processingProgramDAO.listProcessingProgram());
	}
	
	@Test
	public void testListProcessingProgramForCatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddForTry() {
		ProcessingProgram pp = new ProcessingProgram();
		assertEquals(true,processingProgramDAO.add(pp));
	}
	
	/** The ProcessingProgram is added error */
	@Test
	public void testAddForCatch() {
		assertEquals(false,processingProgramDAO.add(null));
	}

	/** The ProcessingProgram is found by Id */
	@Test
	public void testFindProcessingProgramByIdForTry() {
		if(processingProgramDAO.listProcessingProgram().size() != 0)
			assertNotNull(processingProgramDAO.findProcessingProgramById(1));
		else
			assertNull(processingProgramDAO.findProcessingProgramById(0));
	}
	
	@Test
	public void testFindProcessingProgramByIdForCatch() {
		
		fail("Not yet implemented");
	}
	
	/** The ProcessingProgram is deleted*/
	@Test
	public void testDeleteForTry() {
		ProcessingProgram pp = new ProcessingProgram();
		assertEquals(true,processingProgramDAO.delete(pp));
	}
	
	/** The ProcessingProgram is deleted error  */
	@Test
	public void testDeleteForCatch() {
		ProcessingProgram pp = processingProgramDAO.findProcessingProgramById(1);
		assertEquals(false,processingProgramDAO.delete(pp));
	}

}
