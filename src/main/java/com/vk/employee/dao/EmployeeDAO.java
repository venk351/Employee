package com.vk.employee.dao;

import java.util.List;

import com.vk.employee.entity.Employees;

public interface EmployeeDAO {
	
	Employees getEmployee(Integer id);
	
	List<Employees> listEmployee();
	
	void deleteEmployee(Employees employee);
	
	void createEmployee(Employees employee);
	
	void updateEmployee(Employees employee);

}
