package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

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
	//private List<String> roster;
	private List<String> studentIds;
	private String professorId;
	private String StudentTAId;
	
	private String snsTopicArn;
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

	
 	@DynamoDBAttribute(attributeName="snsTopicArn")
 	public String getSNSTopicArn() {
 		return snsTopicArn;
 	}

 	
 	public void setSNSTopicArn(String snsTopicArn) {
 		this.snsTopicArn = snsTopicArn;
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

	@DynamoDBAttribute(attributeName = "studentIds")
	public List<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}

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
