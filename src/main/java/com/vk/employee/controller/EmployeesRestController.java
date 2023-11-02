package com.vk.employee.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.employee.Exception.EmployeeNotFoundException;
import com.vk.employee.entity.Employees;
import com.vk.employee.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeesRestController {
	
	private EmployeeService employeeService;
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	public void setEmployeeDAO(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employees> getEmployees(){
		List<Employees> list = employeeService.listEmployee();
		System.out.println(list);
		return list;
	}
	@PostMapping("/employees")
	public Employees createEmployees(@RequestBody Employees employee) {
		employeeService.createEmployee(employee);
		logger.info("Employee id is created and id is: "+employee.getId());
		return employee;
		
	}
	@GetMapping("/employees/{id}")
	public Employees findEmployee(@PathVariable int id) {
		Employees employee = employeeService.getEmployee(id);
		if(employee!=null)
			return employee;
		else
		{
			throw new EmployeeNotFoundException("Employee is not found for this id:"+id) ;
		}
	}
	@PutMapping("/employees/{id}")
	public Employees updateEmployee(@PathVariable int id,@RequestBody Employees emp) {
		Employees employee = employeeService.getEmployee(id);
		employee.setLastName(emp.getLastName());
		employee.setFirstName(emp.getFirstName());
		employee.setEmailId(emp.getEmailId());
		employeeService.updateEmployee(employee);
		logger.info("Employee details are updated successfully");
		return employee;
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Employees employee = employeeService.getEmployee(id);
		if(employee!=null) {
			employeeService.deleteEmployee(id);
			logger.info("Employee "+id+" id is deleted successfully");
			return "Employee "+id+" id is deleted successfully";
		}
		else
		{
			throw new EmployeeNotFoundException("Employee is not found for this id:"+id);
		}
		
	}
	

}
