package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "board")
public class Board {
	private String Id;
	private String boardId;
	private String courseId;

	private Board() {

	}

	public Board(String boardId, String courseId) {
		this.boardId = boardId;
		this.courseId = courseId;
	}

	@DynamoDBHashKey(attributeName = "Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	@DynamoDBIndexHashKey(globalSecondaryIndexName = "boardId-index", attributeName = "boardId")
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	@DynamoDBAttribute(attributeName = "courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "boardId=" + getBoardId() + ", courseId=" + getCourseId();
	}

}