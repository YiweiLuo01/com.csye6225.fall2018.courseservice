package com.csye6225.fall2018.courseservice.datamodel;

public class Program {
	
	private String programName;
	private int programID;
	private String[] Courses;
	
	public Program() {
		
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public String[] getCourses() {
		return Courses;
	}

	public void setCourses(String[] courses) {
		Courses = courses;
	}
	
}
