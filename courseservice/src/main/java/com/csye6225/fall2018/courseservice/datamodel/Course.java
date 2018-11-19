package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "course")
public class Course {
	private String id;
	private String courseId;
	private String lectureId;
	private String boardId;
	//private String roster;
	//private String[] studentId;
	private String professorId;
	private String StudentTAId;
	
	
	public Course() {
		
	}
	public Course(String courseId, String lectureId, String board, String roster, String studentId, String professorId, String studentTAId) {
		
	}
	
	
	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@DynamoDBIndexHashKey(globalSecondaryIndexName = "courseId-index", attributeName = "courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	
	@DynamoDBAttribute(attributeName = "lectureId")
	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}


	@DynamoDBAttribute(attributeName = "boardId")
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

//	public String getRoster() {
//		return roster;
//	}
//
//	public void setRoster(String roster) {
//		this.roster = roster;
//	}

//	public String[] getStudentId() {
//		return studentId;
//	}
//
//	public void setStudentId(String[] studentId) {
//		this.studentId = studentId;
//	}

	@DynamoDBAttribute(attributeName = "professorId")
	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	@DynamoDBAttribute(attributeName = "StudentTAId")
	public String getStudentTAId() {
		return StudentTAId;
	}

	public void setStudentTAId(String StudentTAId) {
		this.StudentTAId = StudentTAId;
	}

}
