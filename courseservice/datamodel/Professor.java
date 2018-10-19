package com.csye6225.fall2018.courseservice.datamodel;

import java.util.Date;



public class Professor {
	private long professorId;
	private String firstName;
	private String lastName;
	private String department;	
	private Date joiningDate;
	
	public Professor() {
		
	}
	
	public Professor(long professorId, String lastName, String firstName, String department, Date joiningDate) {
		this.professorId = professorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.joiningDate = joiningDate;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
}
