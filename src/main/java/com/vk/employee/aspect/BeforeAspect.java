package com.vk.employee.aspect;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.vk.employee.entity.Employees;


@Aspect
@Component
public class BeforeAspect {
	
	@Before("execution(public * com.vk.employee.controller.EmployeesRestController.getEmployees())")
	public void beforeGetEmployees() {
		
		System.out.println("We are going to get all the employees details in the DB");
	}

}
