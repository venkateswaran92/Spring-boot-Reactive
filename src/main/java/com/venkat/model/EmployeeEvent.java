package com.venkat.model;

import java.util.Date;

public class EmployeeEvent {

	private Employee employee;
	private Date date;

	public EmployeeEvent(Employee employee, Date date2) {
		super();
		this.employee = employee;
		this.date = date2;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
