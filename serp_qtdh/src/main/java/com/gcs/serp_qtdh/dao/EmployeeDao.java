package com.gcs.serp_qtdh.dao;

import java.util.List;

import com.gcs.serp_qtdh.model.Employee;

public interface EmployeeDao {

	Employee findById(int id);

	void saveEmployee(Employee employee);
	
	void deleteEmployeeBySsn(String ssn);
	
	List<Employee> findAllEmployees();

	Employee findEmployeeBySsn(String ssn);

}
