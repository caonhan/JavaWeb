package com.serp.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.entity.FunctionEntity;
import com.serp.entity.RoleEntity;
import com.serp.model.Role;
/**
 * 
 * @author PhiTT
 *
 */
public class RoleServiceTest {

	static AbstractApplicationContext context;
	static RoleService ser;
	static FunctionService funcSer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (RoleService) context.getBean("roleService");
		funcSer= (FunctionService) context.getBean("functionService");
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
		assertNotNull(ser.findById("AD"));
	}
	
	/**
	 * This function is used to test findById() method, expected having Exception
	 */
	@Test(expected= Exception.class)
	public void testFindByIdException() {
		ser.findById(null);
	}

	/**
	 * This function is used to test getAllRoleEntiry() method, expected run successfully.
	 */
	@Test
	public void testGetAllRoleEntiry() {
		assertEquals(true, ser.getAllRoleEntiry().size()>=0);
	}

	/**
	 * This function is used to test getAllRoleEntiry() method, expected run successfully.
	 */
	@Test
	public void testAddRole() {
		RoleEntity en= new RoleEntity();
		en.setRoleId("LT");
		en.setRoleName("Lop Truong");
		assertEquals(true, ser.addRole(en, "admin"));
	}
	
	/**
	 * This function is used to test findById() method, expected having Exception
	 */
	@Test(expected=AssertionError.class)
	public void testAddRoleException() {
		RoleEntity en= new RoleEntity();
		en.setRoleId("AD");
		en.setRoleName("Lop Pho");
		assertEquals(false, ser.addRole(en, "admin"));
	}

	/**
	 * This function is used to test findRoleEntityById() method, expected run successfully.
	 */
	@Test
	public void testFindRoleEntityById() {
		RoleEntity en= ser.findRoleEntityById("AD");
		assertEquals(true, en.getRoleId().equals("AD"));
		en= ser.findRoleEntityById("TCN");
	}
	
	/**
	 * This function is used to test findRoleEntityById() method, expected having Exception
	 */
	@Test(expected=Exception.class)
	public void testFindRoleEntityByIdException() {
		RoleEntity en= ser.findRoleEntityById(null);
		assertNull(en);
	}

	/**
	 * This function is used to test isRoleExistedById() method, expected run successfully.
	 */
	@Test
	public void testIsRoleExistedById() {
		assertEquals(true, ser.isRoleExistedById("AD"));
		assertEquals(false, ser.isRoleExistedById("DN"));
	}
	
	/**
	 * This function is used to test isRoleExistedById() method, expected having Exception
	 */
	@Test(expected=Exception.class)
	public void testIsRoleExistedByIdException() {
		ser.isRoleExistedById(null);
	}

	/**
	 * This function is used to test updateRole() method, expected run successfully.
	 */
	@Test
	public void testUpdateRole() {
		Role role= ser.findById("PX");
		role.setDescription("Đây là role Phó Xưởng");
		assertEquals(true,ser.updateRole(role));
	}
	
	/**
	 * This function is used to test updateRole() method, expected having Exception
	 */
	@Test(expected=Exception.class)
	public void testUpdateRoleException() {
		ser.updateRole(null);
	}

	/**
	 * This function is used to test editRole() method, expected run successfully.
	 */
	@Test
	public void testEditRole() {
		assertEquals(true, ser.editRole(ser.findRoleEntityById("BGD"), "admin"));
	}
	
	/**
	 * This function is used to test editRole() method, expected having Exception
	 */
	@Test(expected=Exception.class)
	public void testEditRoleException() {
		ser.editRole(null, "user0005");
	}

	/**
	 * This function is used to test deleteRole() method, expected run successfully.
	 */
	@Test
	public void testDeleteRole() {
		assertEquals(true, ser.deleteRole("DL"));
	}
	
	/**
	 * This function is used to test deleteRole() method, expected having Exception
	 */
	@Test
	public void testDeleteRoleCaseFalse() {
		assertEquals(false, ser.deleteRole("AD"));
	}

	/**
	 * This function is used to test listAllFuncOfRoleById() method, expected run successfully.
	 */
	@Test
	public void testListAllFuncOfRoleById() {
		assertEquals(true, ser.listAllFuncOfRoleById("AD").size()>=0);
	}
	
	/**
	 * This function is used to test listAllFuncOfRoleById() method, expected run NullPointerException.
	 */
	@Test(expected=NullPointerException.class)
	public void testListAllFuncOfRoleByIdNotExistId() {
		assertNull(ser.listAllFuncOfRoleById("DN"));
	}

	/**
	 * This function is used to test assignFunctionForRole(), expected run successfully, case:
	 * oldList = 0, newList = 0
	 */
	@Test
	public void testAssignFunctionForRoleCase1() {
		ArrayList<FunctionEntity> lst= new ArrayList<FunctionEntity>();
		assertEquals(true, ser.assignFunctionForRole("CS1", lst));
	}

	/**
	 * This function is used to test assignFunctionForRole(), expected run successfully, case:
	 * oldList > 0, newList = 0
	 */
	@Test
	public void testAssignFunctionForRoleCase2() {
		ArrayList<FunctionEntity> lst= new ArrayList<FunctionEntity>();
		assertEquals(true, ser.assignFunctionForRole("CS2", lst));
	}
	
	/**
	 * This function is used to test assignFunctionForRole(), expected run successfully, case:
	 * oldList = 0, newList > 0
	 */
	@Test
	public void testAssignFunctionForRoleCase3() {
		ArrayList<FunctionEntity> lst= new ArrayList<FunctionEntity>();
		FunctionEntity en= funcSer.getAllFunctionEntity().get(0);
		lst.add(en);
		assertEquals(true, ser.assignFunctionForRole("CS3", lst));
	}
	
	/**
	 * This function is used to test assignFunctionForRole(), expected run successfully, case:
	 * oldList > 0, newList > 0
	 */
	@Test
	public void testAssignFunctionForRoleCase4() {
		ArrayList<FunctionEntity> lst= new ArrayList<FunctionEntity>();
		FunctionEntity en= funcSer.getAllFunctionEntity().get(0);
		lst.add(en);
		en= funcSer.getAllFunctionEntity().get(3);
		lst.add(en);
		assertEquals(true, ser.assignFunctionForRole("CS4", lst));
	}
	
	/**
	 * This function is used to test assignFunctionForRole(), expected having Exception
	 */
	@Test(expected=Exception.class)
	public void testAssignFunctionForRoleException() {
		ser.assignFunctionForRole(null, null);
	}
}
