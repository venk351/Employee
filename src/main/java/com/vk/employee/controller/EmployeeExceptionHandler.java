package com.vk.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vk.employee.Exception.EmployeeNotFoundException;
import com.vk.employee.entity.EmployeeErrorResponse;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException
	(EmployeeNotFoundException exce){
		
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		error.setMessage(exce.getMessage());
		error.setStatusCode(HttpStatus.NOT_FOUND.toString());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException
	(Exception exce){
		
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		error.setMessage(exce.getMessage());
		error.setStatusCode(HttpStatus.BAD_REQUEST.toString());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
