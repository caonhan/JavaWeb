package com.gcs.serp_qtdh.controller;

/*import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;*/
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gcs.serp_qtdh.controller.AppController;
/*import com.gcs.serp_qtdh.model.Employee;
import com.gcs.serp_qtdh.service.EmployeeService;*/

import com.gcs.serp_qtdh.controller.UserController;
import com.gcs.serp_qtdh.model.Users;
import com.gcs.serp_qtdh.service.UserService;

public class UserControllerTest {

	@Mock 
	UserService userService;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	AppController appController;
	
	@InjectMocks
	UserController userController;

	@Spy
	List<Users> users = new ArrayList<Users>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		users = getUserList();
	}
	
	@Test
	public void listUsers(){
		when(userService.findAllUsers()).thenReturn(users);
		Assert.assertEquals(userController.listUsers(model), "users");
		Assert.assertEquals(model.get("users"), users);
		verify(userService, atLeastOnce()).findAllUsers();
	}
	
/*	@Test
	public void listEmployees(){
		when(service.findAllEmployees()).thenReturn(employees);
		Assert.assertEquals(appController.listEmployees(model), "allemployees");
		Assert.assertEquals(model.get("employees"), employees);
		verify(service, atLeastOnce()).findAllEmployees();
	}
	
	@Test
	public void newEmployee(){
		Assert.assertEquals(appController.newEmployee(model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Employee)model.get("employee")).getId(), 0);
	}


	@Test
	public void saveEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveEmployee(any(Employee.class));
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
	}

	@Test
	public void saveEmployeeWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
	}

	
	@Test
	public void saveEmployeeWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).saveEmployee(any(Employee.class));
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Employee Axel registered successfully");
	}

	@Test
	public void editEmployee(){
		Employee emp = employees.get(0);
		when(service.findEmployeeBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(appController.editEmployee(anyString(), model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Employee)model.get("employee")).getId(), 1);
	}

	@Test
	public void updateEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateEmployee(any(Employee.class));
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model,""), "registration");
	}

	@Test
	public void updateEmployeeWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model,""), "registration");
	}

	@Test
	public void updateEmployeeWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateEmployee(any(Employee.class));
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Employee Axel updated successfully");
	}
	
	
	@Test
	public void deleteEmployee(){
		doNothing().when(service).deleteEmployeeBySsn(anyString());
		Assert.assertEquals(appController.deleteEmployee("123"), "redirect:/list");
	}*/

	public List<Users> getUserList(){
		Users u1 = new Users();
		u1.setId(1);
		u1.setUserId("US1");
		u1.setDepartment("GCS");
		u1.setEmail("nhanct@gcs-vn.com");
		u1.setAddress("HCMC");
		u1.setFullName("Cao Nhan");
		u1.setPassword("123123abcdf**");
		u1.setPhoneNumber("123454567675");
		u1.setSecretAnswer("ABCD");
		u1.setStatus(true);
		
		Users u2 = new Users();
		u2.setId(1);
		u2.setUserId("US1");
		u2.setDepartment("GCS");
		u2.setEmail("nhanct@gcs-vn.com");
		u2.setAddress("HCMC");
		u2.setFullName("Cao Nhan");
		u2.setPassword("123123abcdf**");
		u2.setPhoneNumber("123454567675");
		u2.setSecretAnswer("ABCD");
		u2.setStatus(true);
		
		return users;
	}
}
