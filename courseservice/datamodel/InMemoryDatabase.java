package com.csye6225.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long, Program> programDB = new HashMap<>();
	private static HashMap<Long, Student> studentDB = new HashMap<>();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<Long, Professor> getProgramDB() {
		return professorDB;
	} 
	
	public static HashMap<Long, Student> getStudentDB (){
		return studentDB;
	}

	
}
