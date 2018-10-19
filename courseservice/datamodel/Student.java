package com.csye6225.fall2018.courseservice.datamodel;

public class Student {
	private String FirstName;
	private String LastName;
	private Long StudentId;
	private Long image;
	private String courseName;
	private String programName;
	
	public Student() {
		
	}
	
	public Student(String FirstName, String LastName, Long StudentId, Long image, String courseName, String programName ) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.StudentId = StudentId;
		this.image = image;
		this.courseName = courseName;
		this.programName = programName;
		
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Long getStudentId() {
		return StudentId;
	}

	public void setStudentId(Long studentId) {
		StudentId = studentId;
	}

	public Long getImage() {
		return image;
	}

	public void setImage(Long image) {
		this.image = image;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
