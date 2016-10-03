package com.serp.dao;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.Role;
/**
 * 
 * @author PhiTT.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleDaoTest {

	static AbstractApplicationContext context;
	static RoleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao= (RoleDao) context.getBean("roleDao");
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
	 * This function is used to test persist() method, expected run successfully.
	 */
	@Test
	public void testPersist() {
		Role role= new Role();
		role.setRoleId("TT");
		role.setRoleName("To Truong");
		dao.persist(role);
		assertNotNull(dao.findById("TT"));
	}
	
	/**
	 * This function is used to test persist() method, expected having RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testPersistException() {
		Role role= null;
		dao.persist(role);
	}

	/**
	 * This function is used to test attachDirty() method, expected run successfully.
	 */
	@Test
	public void testAttachDirty() {
		Role role = dao.findById("PX");
		role.setDescription("Đây là role Phó Xưởng");
		dao.attachDirty(role);
		
		assertEquals(true,dao.findById("PX").getDescription().equals("Đây là role Phó Xưởng"));
	}
	
	/**
	 * This function is used to test attachDirty() method, expected having RuntimeException
	 */
	@Test(expected= RuntimeException.class)
	public void testAttachDirtyException() {
		Role role = null;
		dao.attachDirty(role);
	}

	/**
	 * This function is used to test delete() method, expected having RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
		dao.delete(null);
	}
	
	/**
	 * This function is used to test delete() method, expected run successfully.
	 */
	@Test
	public void testDelete() {
		dao.delete(dao.findById("PGD"));
		assertEquals(dao.findById("PGD"), null);
	}

	/**
	 * This function is used to test findById() method, expected run successfully.
	 */
	@Test
	public void testFindById() {
		assertNotNull(dao.findById("AD"));
		assertNull(dao.findById("XX"));
	}
	
	/**
	 * This function is used to test findById() method, expected having RuntimeException
	 */
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		dao.findById(null);
	}

	/**
	 * This function is used to test getAllRole() method, expected run successfully.
	 */
	@Test
	public void testGetAllRole() {
		assertEquals(true,dao.getAllRole().size()>=0);
	}
	
	/**
	 * This function is used to test getAllRole() method, expected having Exception
	 */
	@Test(expected=Exception.class)
	public void ztestGetAllRoleException() {
		dao= null;
		dao.getAllRole();
	}
}
