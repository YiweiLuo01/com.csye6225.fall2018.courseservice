package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentService {
	static HashMap<String, Student> studentMap = InMemoryDatabase.getStudentDB();
	static DynamoDbConnector dynamoDb;
	static DynamoDBMapper mapper; // static DynamoDbConnector dynamoDb;

	public StudentService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public static List<Student> getAllStudents() {
		// Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("studentId-index")
				.withConsistentRead(false);
		List<Student> list = mapper.scan(Student.class, scanExpression);
		return list;

	}

	public static Student studentAdding(Student student) {

		Student newStudent = new Student();

		newStudent.setFirstName(newStudent.getFirstName());
		newStudent.setLastName(newStudent.getLastName());
		newStudent.setCourseName(newStudent.getCourseName());
		newStudent.setProgramName(newStudent.getProgramName());
		newStudent.setStudentId(newStudent.getLastName().charAt(0) + "." + newStudent.getFirstName());
		mapper.save(newStudent);

		System.out.println("Student added:");
		System.out.println(newStudent.toString());

		return newStudent;
	}

	public static Student getStudentById(String studentId) {
		return studentMap.get(studentId);
	}

	// Getting One Professor
	public Student getStudent(String studentId) {
		List<Student> list = getStudentFromDDB(studentId);
		return list.size() != 0 ? list.get(0) : null;
	}

//	public static Student studentDeleting(String studentId) {
//		Student deletStudent  = studentMap.get(studentId);
//		if(deletStudent != null){
//			studentMap.remove(studentId);
//			return deletStudent;
//		}else {
//			System.out.println("cannot delete NullStudent");
//			return deletStudent;
//		}
//	}
	public static Student studentDeleting(String studentId) {
		List<Student> list = getStudentFromDDB(studentId);
		Student student = null;
		if (list.size() != 0) {
			student = list.get(0);
			mapper.delete(student);
			Student deletStudent = mapper.load(Student.class, student.getId());

			if (deletStudent == null) {
				System.out.println("Done - The Student is deleted.");
				System.out.println(student.toString());
			}
		}

		return student;
	}

	public static Student studentUpdating(String studentId, Student newStudent) {
//		Student oldStudent = studentMap.get(studentId);
//		
//		if(oldStudent != null && newStudent != null) {
//			String oldId = oldStudent.getStudentId();
//			newStudent.setStudentId(oldId);
//			studentMap.put(oldId, newStudent);
//			return newStudent;
//		}else {
//			return oldStudent;
//		}

		List<Student> list = getStudentFromDDB(studentId);
		Student oldStudent = null;
		if (list.size() != 0) {
			oldStudent = list.get(0);
			oldStudent.setFirstName(newStudent.getFirstName());
			oldStudent.setLastName(newStudent.getLastName());
			oldStudent.setCourseName(newStudent.getCourseName());
//			oldProf.setJoiningDate(prof.getJoiningDate());
			mapper.save(oldStudent);
			System.out.println("student updated:");
			System.out.println(oldStudent.toString());
		}

		return oldStudent;
	}

	public static List<Student> getStudentsByCourse(String courseId) {
		ArrayList<Student> list = new ArrayList<>();
		for (Student student : studentMap.values()) {
			if (student.getCourseName().equals(courseId)) {
				list.add(student);
			}
		}
		return list;
	}

	public static List<Student> getStudentFromDDB(String profId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(profId));

		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index").withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1").withExpressionAttributeValues(eav);

		List<Student> list = mapper.query(Student.class, queryExpression);
		return list;
	}
}
