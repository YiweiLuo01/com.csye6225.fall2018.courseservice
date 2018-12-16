package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;

public class CourseService {

	static HashMap<Long, Course> courseMap = new HashMap<>();
	static DynamoDbConnector dynamoDb;
	static DynamoDBMapper mapper;

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

	public static Course courseAdding(Course course) {
		Course newCourse = new Course();
		newCourse.setCourseId(course.getCourseId());
		newCourse.setBoard(course.getBoard());
		newCourse.setStudentId(course.getStudentId());
		newCourse.setProfessorId(course.getProfessorId());
		newCourse.setStudentTAId(course.getStudentTAId());
		newCourse.setLectureId(course.getLectureId());
		mapper.save(newCourse);

		System.out.println("course added");
		System.out.println(newCourse.toString());

		return newCourse;
	}

	public static Course getCourseById(String courseId) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index").withConsistentRead(false).withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(map);

		List<Course> list = mapper.query(Course.class, queryExpression);
		return list.size() != 0 ? list.get(0) : null;
	}

	public static Course courseDeleting(String courseId) {

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

	public static Course courseUpdating(String courseId, Course newCourse) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index").withConsistentRead(false).withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(map);

		List<Course> list = mapper.query(Course.class, queryExpression);

		Course oldCourse = null;

		if (list.size() != 0) {

			oldCourse = list.get(0);
			oldCourse.setBoard(newCourse.getBoard());
			oldCourse.setStudentId(newCourse.getStudentId());
			oldCourse.setProfessorId(newCourse.getProfessorId());
			oldCourse.setStudentTAId(newCourse.getStudentTAId());
			oldCourse.setLectureId(newCourse.getLectureId());

			mapper.save(oldCourse);
			System.out.println("Course updated.");
			System.out.println(oldCourse.toString());
		}

		return oldCourse;

	}

}
