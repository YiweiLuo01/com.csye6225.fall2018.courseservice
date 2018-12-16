package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.EmailAnnouncement;
import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;

public class CourseService {

	HashMap<Long, Course> courseMap = new HashMap<>();
	DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public CourseService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<Course> getAllCourses() {
		// get list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("courseId-index")
				.withConsistentRead(false);

		List<Course> list = mapper.scan(Course.class, scanExpression);
		return list;
	}

	public Course courseAdding(Course course) {
		Course newCourse = new Course();
		String topicArn = EmailAnnouncement.createTopic("course" + course.getCourseId());
		newCourse.setCourseId(course.getCourseId());
		newCourse.setBoardId(course.getBoardId());
		newCourse.setStudentIds(course.getStudentIds());
		newCourse.setProfessorId(course.getProfessorId());
		newCourse.setStudentTAId(course.getStudentTAId());
		newCourse.setLectureId(course.getLectureId());
		newCourse.setSNSTopicArn(topicArn);

		mapper.save(newCourse);
		
		if(!newCourse.getBoardId().equals("")) {
			Board board = new Board(newCourse.getBoardId(), newCourse.getCourseId());
			BoardService boardService = new BoardService();
			boardService.addBoard(board);
			System.out.println("board added");

			
			
		}
		System.out.println(newCourse.toString());

		return newCourse;
		
	}

	public Course getCourseById(String courseId) {
		return mapper.load(Course.class, courseId);
	}

	public Course courseDeleting(String courseId) {

		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index").withConsistentRead(false).withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(map);

		List<Course> list = mapper.query(Course.class, queryExpression);
		Course courseToDelete = null;
		if (list.size() != 0) {
			courseToDelete = list.get(0);
			mapper.delete(courseToDelete);
			Course deletedCourse = mapper.load(Course.class, courseToDelete.getCourseId());

			if (deletedCourse == null) {
				System.out.println("course is deleted.");
				System.out.println(courseToDelete.toString());
			}
		}

		return courseToDelete;
	}

	public Course courseUpdating(String courseId, Course newCourse) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index").withConsistentRead(false).withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(map);

		List<Course> list = mapper.query(Course.class, queryExpression);

		Course oldCourse = null;

		if (list.size() != 0) {

			oldCourse = list.get(0);
			oldCourse.setBoardId(newCourse.getBoardId());
			oldCourse.setStudentIds(newCourse.getStudentIds());
			oldCourse.setProfessorId(newCourse.getProfessorId());
			oldCourse.setStudentTAId(newCourse.getStudentTAId());
			oldCourse.setLectureId(newCourse.getLectureId());
			oldCourse.setSNSTopicArn(newCourse.getSNSTopicArn());
			// oldCourse.setRoster(newCourse.getRoster());
			oldCourse.setCourseId(newCourse.getCourseId());

			mapper.save(oldCourse);
			System.out.println("Course updated.");
			System.out.println(oldCourse.toString());
		}

		return oldCourse;

	}

	public List<Course> getCourseFromDDB(String courseId) {

		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index").withConsistentRead(false).withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(eav);

		List<Course> list = mapper.query(Course.class, queryExpression);
		return list;
	}

}
