package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

public class Program {

	private String programName;
	private String programID;
	private List<Course> programCourse;

	public Program(String programName, List<Course> programCourse) {
		this.programCourse = programCourse;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<Course> getProgramCourses() {
		return programCourse;
	}

	public void setProgramCourses(List<Course> programCourse) {
		this.programCourse = programCourse;
	}

	public String getProgramID() {
		return programID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}
//
//	public Object getProgramsByCourseId() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
