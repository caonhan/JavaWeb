package com.serp.testRun;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.ProcessingProgram;
import com.serp.service.ProcessingProgramService;

public class ProcessingProgramServiceImplTest {
	
	static AbstractApplicationContext context;
	static ProcessingProgramService processingProgramService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		processingProgramService = (ProcessingProgramService) context.getBean("processingProgramService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	/** The listProcessingProgram is compared with null*/
	@Test
	public void testListProcessingProgramForTry() {
		assertNotNull(processingProgramService.listProcessingProgram());
	}
	
	@Test
	public void testListProcessingProgramForCatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddForTry() {
		fail("Not yet implemented");
	}
	
	/** The ProcessingProgram is added error */
	@Test
	public void testAddForCatch() {
		int initSize = processingProgramService.listProcessingProgram().size();
		processingProgramService.add(null);
		assertEquals(initSize, processingProgramService.listProcessingProgram().size());
	}

	/** The ProcessingProgram is found by Id */
	@Test
	public void testFindProcessingProgramByIdForTry() {
		if(processingProgramService.listProcessingProgram().size() != 0)
			assertNotNull(processingProgramService.findProcessingProgramById(1));
		else
			assertNull(processingProgramService.findProcessingProgramById(0));
	}
	
	@Test
	public void testFindProcessingProgramByIdForCatch() {
		
		fail("Not yet implemented");
	}
	
	/** The ProcessingProgram is deleted*/
	@Test
	public void testDeleteForTry() {
		ProcessingProgram pp = new ProcessingProgram();
		processingProgramService.delete(pp);
	}
	
	/** The ProcessingProgram is deleted error  */
	@Test
	public void testDeleteForCatch() {
		ProcessingProgram pp = processingProgramService.findProcessingProgramById(1);
		processingProgramService.delete(pp);
	}

}
