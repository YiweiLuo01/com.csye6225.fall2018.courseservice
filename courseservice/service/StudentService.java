package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Student;;


public class StudentService {
	static HashMap<Long, Student> studentMap = InMemoryDatabase.getStudentDB();
	
	public static List<Student> getAllStudents(){
		List<Student> studentList = new ArrayList<Student>();
		
		
		for(Student student: studentMap.values()) {
			studentList.add(student);
			
		}
		return studentList;
		
		
	}
	
	public static Student studentAdding(Student student){
		
		Long studentId = (long) (studentMap.size() + 1);
		
		 studentMap.put(studentId, student);
		 return student;	
	}
	
	public static Student getStudentById(Long studentId) {
		return studentMap.get(studentId);
	}
	
	public static Student studentDeleting(Long studentId) {
		Student deletStudent  = studentMap.get(studentId);
		if(deletStudent != null){
			studentMap.remove(studentId);
			return deletStudent;
		}else {
			System.out.println("cannot delete NullStudent");
			return deletStudent;
		}
	}
	
	public static Student studentUpdating(Long studentId, Student newStudent) {
		Student oldStudent = studentMap.get(studentId);
		
		if(oldStudent != null && newStudent != null) {
			Long oldId = oldStudent.getStudentId();
			newStudent.setStudentId(oldId);
			studentMap.put(oldId, newStudent);
			return newStudent;
		}else {
			return oldStudent;
		}
		
	}
	
	public static List<Student> getStudentsByCourse(Long courseId) {
		List<Student> studentList = new ArrayList<Student>();
		if(courseId != null) {
			for(Student student : studentMap.values()) {
				
				if(student.getStudentId() == courseId) {
						studentList.add(student);
				}		
				
			}
		}
		return studentList;	
	}
}
