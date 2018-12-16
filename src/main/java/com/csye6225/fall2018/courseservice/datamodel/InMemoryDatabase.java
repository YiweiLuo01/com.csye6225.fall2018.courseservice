package com.csye6225.fall2018.courseservice.datamodel;

import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<String, Professor> professorDB = new HashMap<>();
	private static HashMap<String, Student> studentDB = new HashMap<>();

	public static HashMap<String, Professor> getProfessorDB() {
		return professorDB;
	}

	public static HashMap<String, Professor> getProgramDB() {
		return professorDB;
	}

	public static HashMap<String, Student> getStudentDB() {
		return studentDB;
	}

}