package com.csye6225.fall2018.courseservice.datamodel;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "Program")
public class Program {
	private String id;
	private String programId;
	private String courseId;
	private String programName;
	private String studentId;
	
	//private List<Course> programCourse;
	
	public Program() {
		
	}
	
	
	
	
	
	public Program(String id, String programId, String programName, String courseId, String studentId ) {
		this.id = id;
		this.programId = programId;
		this.programName = programName;
		this.courseId = courseId;
		this.studentId = studentId;
	}
	
	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBIndexHashKey(globalSecondaryIndexName = "programId-index", attributeName = "programId")
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	@DynamoDBAttribute(attributeName = "programName")
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

		
	@DynamoDBAttribute(attributeName = "courseId")
	public String getcourseId() {
		return courseId;
	}

	public void setcourseId(String courseId) {
		this.courseId = courseId;
	}
	

	
//	public List<Course> getProgramCourses() {
//		return programCourse;
//	}
//
//	public void setProgramCourses(List<Course> programCourse) {
//		this.programCourse = programCourse;
//	}
//	
	@DynamoDBAttribute(attributeName = "studentId")
	public String getstudentId() {
		return studentId;
	}

	public void setstudentId(String studentId) {
		this.studentId = studentId;
	}
	



}
