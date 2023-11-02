package com.vk.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vk.employee.Exception.EmployeeNotFoundException;
import com.vk.employee.entity.Employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeesImpl implements EmployeeDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeesImpl(EntityManager thisEntityManager) {
		this.entityManager = thisEntityManager;
	}

	@Override
	public Employees getEmployee(Integer id) {
		Employees employee = entityManager.find(Employees.class, id);
		return employee;
	}

	@Override
	public List<Employees> listEmployee() {
		TypedQuery<Employees> query = entityManager.createQuery("from Employees", Employees.class);
		List<Employees> listEmployee = query.getResultList();
		return listEmployee;
	}

	@Override
	public void deleteEmployee(Employees employee) {
		entityManager.remove(employee);
	}

	@Override
	public void createEmployee(Employees employee) {
		entityManager.persist(employee);
	}

	@Override
	public void updateEmployee(Employees employee) {
		entityManager.merge(employee);	
	}

}
