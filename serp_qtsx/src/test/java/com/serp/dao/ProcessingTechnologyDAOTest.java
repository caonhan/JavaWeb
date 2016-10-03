package com.serp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;
import com.serp.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessingTechnologyDAOTest {

	static ProcessingTechnologyDAO proTechnology;
	static ElementDAO element;
	static AbstractApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		proTechnology = (ProcessingTechnologyDAO) context.getBean("processingTechnologyDao");
		element = (ElementDAO) context.getBean("elementDao");
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

	}

	@Test
	public void testListProcessingEditor_manager() {
		List<ProcessingTechnology> lstPro = proTechnology.listProcessingEditor_manager("waiting approve");
		assertEquals(1, lstPro.size());

	}

	@Test
	public void testListProcessingTechnologies() {
		List<ProcessingTechnologyDetail> lstPro = proTechnology.listProcessingTechnologies(1);
		assertEquals(0, lstPro.size());
	}

	@Test
	public void testCheckProcessTechnology() {
		User u = new User();
		u.setUserId("tttcn");
		ProcessingTechnology processingTechnology = new ProcessingTechnology();
		processingTechnology.setPtId(2);
		processingTechnology.setPtStatus("waiting approve");
		processingTechnology.setPtNote("note");
		processingTechnology.setUserByPtLeaderAccept(u);
		processingTechnology.setElement(element.findById("A"));
		proTechnology.checkProcessTechnology(processingTechnology);

		ProcessingTechnology pro = proTechnology.findById(2);
		assertEquals("waiting approve", pro.getPtStatus());
	}

	@Test
	public void testCheckProcessTechnologyEx() {
		proTechnology.checkProcessTechnology(null);
	}

	@Test
	public void testApprovalTechnology() {
		User u = new User();
		u.setUserId("tttcn");
		ProcessingTechnology processingTechnology = new ProcessingTechnology();
		processingTechnology.setPtId(2);
		processingTechnology.setPtStatus("approve");
		processingTechnology.setPtNote("note");
		processingTechnology.setUserByPtManagerAccept(u);
		processingTechnology.setElement(element.findById("A"));
		proTechnology.approvalTechnology(processingTechnology);

		ProcessingTechnology pro = proTechnology.findById(2);
		assertEquals("approve", pro.getPtStatus());
	}

	@Test
	public void testApprovalTechnologyEx() {
		ProcessingTechnology processingTechnology = new ProcessingTechnology();
		proTechnology.approvalTechnology(processingTechnology);
		assertEquals(null, processingTechnology.getPtId());
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
		assertEquals(null, pro);

	}

	@Test
	public void testFindByIdNull() {
		assertNull(proTechnology.findById(0));
	}

	@Test
	public void testFindProgram() {
		ProcessingProgram program = proTechnology.findProgram("A");
		assertEquals("A", program.getElement().getEId());
	}

	@Test
	public void testFindProgramEx() {
		assertNull(proTechnology.findProgram(null));
	}

	@Test
	public void testFindProgramDetail() {
		List<ProcessingProgramDetail> lstPro = proTechnology.findProgramDetail(2);
		assertEquals(1, lstPro.size());
	}

}
