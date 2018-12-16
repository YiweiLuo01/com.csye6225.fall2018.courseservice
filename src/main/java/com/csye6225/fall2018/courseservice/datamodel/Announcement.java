package com.csye6225.fall2018.courseservice.datamodel;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Announcement")

public class Announcement {
	private String id;
	private String announcementId;
	private String announceContent;
	private String boardId;
	private String courseId;
	
	public Announcement() {

	}

	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBIndexRangeKey(globalSecondaryIndexName = "boardId-announcementId-index", attributeName = "announcementId")
	public String getAnnounceId() {
		return announcementId;
	}

	public void setAnnounceId(String announcementId) {
		this.announcementId = announcementId;
	}

	@DynamoDBAttribute(attributeName = "announcementContent")
	public String getAnnounceContent() {
		return announceContent;
	}

	public void setAnnounceContent(String announceContent) {
		this.announceContent = announceContent;
	}

	@DynamoDBIndexHashKey(globalSecondaryIndexName = "boardId-announcementId-index", attributeName = "boardId")
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

}