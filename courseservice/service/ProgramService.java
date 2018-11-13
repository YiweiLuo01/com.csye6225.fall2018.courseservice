package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Program;

public class ProgramService {
	static HashMap<Long, Course> programMap = new HashMap<>();
	static DynamoDbConnector dynamoDb;
	static DynamoDBMapper mapper;

	public ProgramService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	public List<Program> getAllProgrames() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("programId-index")
				.withConsistentRead(false);

		List<Program> list = mapper.scan(Program.class, scanExpression);
		return list;
	}

	public static Program getProgramById(String programId) {

		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(programId));

		DynamoDBQueryExpression<Program> queryExpression = new DynamoDBQueryExpression<Program>()
				.withIndexName("programId-index").withConsistentRead(false)
				.withKeyConditionExpression("programId = :v1").withExpressionAttributeValues(map);

		List<Program> list = mapper.query(Program.class, queryExpression);
		return list.size() != 0 ? list.get(0) : null;
	}

	public static Program programAdding(Program program) {
		Program newProgram = new Program(null, null);
		newProgram.setProgramName(program.getProgramName());
		newProgram.setProgramID(program.getProgramID());

		mapper.save(newProgram);

		System.out.println("newProgram added");
		System.out.println(newProgram.toString());

		return newProgram;
	}

	public static Program programDeleting(String programId) {

		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(programId));

		DynamoDBQueryExpression<Program> queryExpression = new DynamoDBQueryExpression<Program>()
				.withIndexName("programId-index").withConsistentRead(false)
				.withKeyConditionExpression("programId = :v1").withExpressionAttributeValues(map);

		List<Program> list = mapper.query(Program.class, queryExpression);
		Program programToDelete = null;
		if (list.size() != 0) {
			programToDelete = list.get(0);
			mapper.delete(programToDelete);
			Program deletedProgram = mapper.load(Program.class, programToDelete.getProgramID());

			if (deletedProgram == null) {
				System.out.println("Program is deleted.");
				System.out.println(programToDelete.toString());
			}
		}

		return programToDelete;
	}

	public static Program programUpdating(String programId, Program newProgram) {
		HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":v1", new AttributeValue().withS(programId));

		DynamoDBQueryExpression<Program> queryExpression = new DynamoDBQueryExpression<Program>()
				.withIndexName("programId-index").withConsistentRead(false)
				.withKeyConditionExpression("programId = :v1").withExpressionAttributeValues(map);

		List<Program> list = mapper.query(Program.class, queryExpression);

		Program oldProgram = null;

		if (list.size() != 0) {

			oldProgram = list.get(0);
			oldProgram.setProgramID(newProgram.getProgramID());
			oldProgram.setProgramName(newProgram.getProgramName());
			oldProgram.setProgramCourses(newProgram.getProgramCourses());

			mapper.save(oldProgram);
			System.out.println("Course updated:");
			System.out.println(oldProgram.toString());
		}

		return oldProgram;

	}

}
