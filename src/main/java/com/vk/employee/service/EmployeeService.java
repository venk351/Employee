package com.vk.employee.service;

import java.util.List;


import com.vk.employee.entity.Employees;


public interface EmployeeService {
	
	Employees getEmployee(Integer id);
	
	List<Employees> listEmployee();
	
	void deleteEmployee(int id);
	
	void createEmployee(Employees employee);
	
	void updateEmployee(Employees employee);

}
