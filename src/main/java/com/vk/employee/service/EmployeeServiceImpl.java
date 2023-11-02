package com.vk.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.employee.Exception.EmployeeNotFoundException;
import com.vk.employee.dao.EmployeeRepository;
import com.vk.employee.entity.Employees;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employees getEmployee(Integer id) {
		Optional<Employees> employee = employeeRepository.findById(id);
		Employees theEmployee = null;
		if(employee.isPresent()) {
			theEmployee = employee.get();
			return theEmployee;
		}
		throw new EmployeeNotFoundException("Employee id "+id+" is not found!");
	}

	@Override
	public List<Employees> listEmployee() {
		List<Employees> empList = employeeRepository.findAll();
		return empList;
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void createEmployee(Employees employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Employees employee) {
		employeeRepository.save(employee);	
	}

}
