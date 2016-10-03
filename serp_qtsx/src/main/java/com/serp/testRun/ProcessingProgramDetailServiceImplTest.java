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
import com.serp.service.ProcessingProgramDetailService;
import com.serp.service.ProcessingProgramService;
import com.serp.service.UserService;

public class ProcessingProgramDetailServiceImplTest {
	static AbstractApplicationContext context;
	static ProcessingProgramDetailService processingProgramDetailService;
	static ProcessingProgramService processingProgramService;
	static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		processingProgramDetailService = (ProcessingProgramDetailService) context.getBean("processingProgramDetailService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	/** The number of listProcessingProgramDetail is compared with 0*/
	@Test
	public void testListProcessingProgramDetailForTry() {
		assertNotNull(processingProgramDetailService.listProcessingProgramDetail(0).size());
	}
	
	@Test
	public void testListProcessingProgramDetailForCatch() {
	}
	
	@Test
	public void testAddForTry() {
		ProcessingProgram pp = processingProgramService.findProcessingProgramById(1);
		ProcessingProgramDetail ppd = new ProcessingProgramDetail();
		ppd.setProcessingProgram(pp);
		processingProgramDetailService.add(ppd);
	}
	
	/** The ProcessingProgramDetail is added by error*/
	@Test
	public void testAddForCarch() {
		processingProgramDetailService.add(new ProcessingProgramDetail());
	}
	
	@Test
	public void testAddForCatch() {
	}
	
	/** The result of searchProcessingProgramDetail is checked*/
	@Test
	public void testSearchProcessingProgramDetailForTry() {
		if(processingProgramDetailService.listProcessingProgramDetail(1).size() != 0)
		{
			assertNotNull(processingProgramDetailService.searchProcessingProgramDetail(1));
		}else
		{
			assertNull(processingProgramDetailService.searchProcessingProgramDetail(0));
		}	
	}
	
	@Test
	public void testSearchProcessingProgramDetailForCatch() {
	}

	/** The ProcessingProgramDetail is deleted*/
	@Test
	public void testDeleteForTry() {
		ProcessingProgramDetail pp = new ProcessingProgramDetail();
		processingProgramDetailService.delete(pp);
	}
	
	/** The ProcessingProgram is deleted error*/
	@Test
	public void testDeleteForCatch() {
		processingProgramDetailService.delete(null);
	}

}
