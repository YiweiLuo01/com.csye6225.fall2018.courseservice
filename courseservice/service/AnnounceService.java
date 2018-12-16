package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Announcement;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;

public class AnnounceService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public AnnounceService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	// Get all announcements
	// GET "..webapi/announcements"
	public List<Announcement> getAllAnnouncements() {

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withIndexName("boardId-announcementId-index").withConsistentRead(false);

		List<Announcement> list = mapper.scan(Announcement.class, scanExpression);
		return list;
	}

	// get an announcement
	public Announcement getAnnounceById(String announcementId, String boardId) {
		List<Announcement> list = getAnnouncementFromDDB(announcementId, boardId);
		return list.size() != 0 ? list.get(0) : null;
	}

	// add an announcement
	public Announcement addAnnouncement(Announcement announcement) {
		Announcement newAnnouce = new Announcement();
		newAnnouce.setBoardId(announcement.getBoardId());
		newAnnouce.setAnnounceId(announcement.getAnnounceId());
		newAnnouce.setAnnounceContent(announcement.getAnnounceContent());
		mapper.save(newAnnouce);

		return newAnnouce;
	}

	public Announcement deleteAnnounce(String boardId, String announcementId) {
		List<Announcement> list = getAnnouncementFromDDB(boardId, announcementId);
		Announcement announce = null;
		if (list.size() != 0) {
			announce = list.get(0);
			mapper.delete(announce);
			Announcement announceDeleting = mapper.load(Announcement.class, announce.getId());

			if (announceDeleting == null) {
				System.out.println("announcement is deleted.");
				System.out.println(announce.toString());
			}
		}

		return announce;
	}

	public Announcement updateAnnounc(String boardId, String announcementId, Announcement oldAnnounce) {

		List<Announcement> list = getAnnouncementFromDDB(boardId, announcementId);
		Announcement newAnnounce = null;

		if (list.size() != 0) {
			newAnnounce = list.get(0);
			newAnnounce.setAnnounceContent(oldAnnounce.getAnnounceContent());
			;
			mapper.save(newAnnounce);

			System.out.println("Annouce updated.");
			System.out.println(newAnnounce.toString());

		}

		return newAnnounce;
	}

	public List<Announcement> getAnnounceByBoardId(String boardId) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index").withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1").withExpressionAttributeValues(map);

		List<Announcement> list = mapper.query(Announcement.class, queryExpression);
		return list;
	}

	public void deleteAnnouncementsByBoardId(String boardId) {
		List<Announcement> announcements = getAnnounceByBoardId(boardId);

		if (announcements.size() != 0) {
			mapper.batchDelete(announcements);
		}
	}

	private List<Announcement> getAnnouncementFromDDB(String boardId, String announcementId) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(boardId));
		map.put(":v2", new AttributeValue().withS(announcementId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index").withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
				.withExpressionAttributeValues(map);

		List<Announcement> list = mapper.query(Announcement.class, queryExpression);
		return list;
	}

}
