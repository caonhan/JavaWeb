package com.serp.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;


public class ProcessingTechnologyServiceImplTest {

	static ProcessingTechnologyService proTechnology;
	static UserLoginService userService;
	static ElementService elementService;
	static AbstractApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		proTechnology = (ProcessingTechnologyService) context.getBean("processingTechnologyService");
		userService = (UserLoginService) context.getBean("UserLoginService");
		elementService = (ElementService) context.getBean("elementService");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	public void testListProcessingEditor_leader() {
		List<ProcessingTechnology> lstPro = proTechnology.listProcessingEditor_leader("new");
		assertEquals(0, lstPro.size());
	}
	
	@Test
	public void testListProcessingEditor_leaderEx() {
		List<ProcessingTechnology> lstPro = proTechnology.listProcessingEditor_leader(null);
		assertEquals(0, lstPro.size());
	}
	
	@Test
	public void testListProcessingEditor_manager() {
		List<ProcessingTechnology> lstPro = proTechnology.listProcessingEditor_manager("waiting approve");
		assertEquals(0, lstPro.size());
		
	}

	@Test
	public void testListProcessingTechnologies() {
		List<ProcessingTechnologyDetail> lstPro = proTechnology.listProcessingTechnologies(1);
		assertEquals(0, lstPro.size());
	}

	@Test
	public void testCheckProcessTechnology() {
		ProcessingTechnology processingTechnology = new ProcessingTechnology();		
		processingTechnology.setPtId(2);
		processingTechnology.setPtStatus("waiting approve");
		processingTechnology.setPtNote("note");
		processingTechnology.setUserByPtLeaderAccept(userService.findById("tttcn"));
		processingTechnology.setElement(elementService.findById("A"));
		proTechnology.checkProcessTechnology(processingTechnology);
		
		ProcessingTechnology pro = proTechnology.findById(2);
		assertEquals("waiting approve",pro.getPtStatus());
		
	}
	
	@Test
	public void testCheckProcessTechnologyEx() {
		ProcessingTechnology processingTechnology = new ProcessingTechnology();		
		proTechnology.checkProcessTechnology(processingTechnology);
		assertEquals(null,processingTechnology.getPtId());
	}
	
	@Test
	public void testApprovalTechnology() {
		ProcessingTechnology processingTechnology = new ProcessingTechnology();
		processingTechnology.setPtId(2);
		processingTechnology.setPtStatus("approve");
		processingTechnology.setPtNote("note");
		processingTechnology.setUserByPtManagerAccept(userService.findById("tttcn"));
		processingTechnology.setElement(elementService.findById("A"));
		proTechnology.approvalTechnology(processingTechnology);
		
		ProcessingTechnology pro = proTechnology.findById(2);
		assertEquals("approve",pro.getPtStatus());
	}

	@Test
	public void testApprovalTechnologyEx() {
		ProcessingTechnology processingTechnology = new ProcessingTechnology();
		proTechnology.approvalTechnology(processingTechnology);
		assertEquals(null,processingTechnology.getPtId());
	}
	@Test
	public void testFindById() {
		ProcessingTechnology pro = new ProcessingTechnology();
		pro.setPtId(1);
		pro.setPtNote("ptNote");
		ProcessingTechnology pro1 = proTechnology.findById(2);
		assertEquals("A", pro1.getElement().getEId());
	}
	
	@Test
	public void testFindByIdEx() {
		ProcessingTechnology pro = proTechnology.findById(null);
		assertEquals(null,pro);
	}


	
	@Test
	public void testHasCheckTechnologyRoleKTCN() {
		String role = proTechnology.hasCheckTechnologyRole(userService.findById("tttcn"));
		assertEquals("KTCN",role);
	}
	
	@Test
	public void testHasCheckTechnologyRoleDCN() {
		String role = proTechnology.hasCheckTechnologyRole(userService.findById("txuong1"));
		assertEquals("DCN",role);
	}
	@Test
	public void testHasCheckTechnologyRoleNull() {
		
		String role = proTechnology.hasCheckTechnologyRole(null);
		assertEquals(null, role);
	}

	@Test
	public void testFindProgram() {
		ProcessingProgram program = proTechnology.findProgram("A");
		assertEquals("A",program.getElement().getEId());
	}
	
	@Test
	public void testFindProgramEx() {
		ProcessingProgram program = proTechnology.findProgram("0");
		assertEquals(null,program);
	}

	@Test
	public void testFindProgramDetail() {
		List<ProcessingProgramDetail> lstPro = proTechnology.findProgramDetail(2);
		assertEquals(1, lstPro.size());
	}

}
