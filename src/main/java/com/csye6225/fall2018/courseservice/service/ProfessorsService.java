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
//import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {

	static DynamoDbConnector dynamoDb;
	static HashMap<String, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	DynamoDBMapper mapper;

	public ProfessorsService() {
		dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	// Getting a list of all professor
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {
		// Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("professorId-index")
				.withConsistentRead(false);

		List<Professor> list = mapper.scan(Professor.class, scanExpression);
		return list;
	}

	// Adding a professor
	public Professor addProfessor(Professor prof) {
		Professor newProf = new Professor();
		newProf.setFirstName(prof.getFirstName());
		newProf.setLastName(prof.getLastName());
		newProf.setDepartment(prof.getDepartment());
		newProf.setJoiningDate(prof.getJoiningDate());
		newProf.setProfessorId(prof.getProfessorId());
		mapper.save(newProf);

		System.out.println("Professor added:");
		System.out.println(newProf.toString());

		return newProf;
	}

	// Getting One Professor
	public Professor getProfessor(String profId) {
		List<Professor> list = getProfessorFromDDB(profId);
		return list.size() != 0 ? list.get(0) : null;
	}

	// Deleting a professor
	public Professor professorDeleting(String profId) {
		List<Professor> list = getProfessorFromDDB(profId);
		Professor prof = null;
		if (list.size() != 0) {
			prof = list.get(0);
			mapper.delete(prof);
			Professor deletedProf = mapper.load(Professor.class, prof.getId());

			if (deletedProf == null) {
				System.out.println("Done - The professor is deleted.");
				System.out.println(prof.toString());
			}
		}

		return prof;
	}

	// Updating Professor Info
	public Professor professorUpdating(String profId, Professor prof) {
		List<Professor> list = getProfessorFromDDB(profId);
		Professor oldProf = null;
		if (list.size() != 0) {
			oldProf = list.get(0);
			oldProf.setFirstName(prof.getFirstName());
			oldProf.setLastName(prof.getLastName());
			oldProf.setDepartment(prof.getDepartment());
			mapper.save(oldProf);
			System.out.println("professor updated:");
			System.out.println(oldProf.toString());
		}

		return oldProf;
	}

	// Get professors in a department
	public List<Professor> getProfessorsByDepartment(String department) {
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list;
	}

	public List<Professor> getProfessorFromDDB(String profId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(profId));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
				.withIndexName("professorId-index").withConsistentRead(false)
				.withKeyConditionExpression("professorId = :v1").withExpressionAttributeValues(eav);

		List<Professor> list = mapper.query(Professor.class, queryExpression);
		return list;
	}
}
	



