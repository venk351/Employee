package com.vk.employee.entity;

public class EmployeeErrorResponse {
	
	private String statusCode;
	private String message;
	private long timeStamp;
	public EmployeeErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeErrorResponse(String message, String statusCode, long timeStamp) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long l) {
		this.timeStamp = l;
	}
	
	

}
