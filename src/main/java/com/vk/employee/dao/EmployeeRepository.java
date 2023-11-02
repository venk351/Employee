package com.vk.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.employee.entity.Employees;

public interface EmployeeRepository extends JpaRepository<Employees, Integer>{

}
