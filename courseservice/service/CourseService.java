package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;


public class CourseService {
	
	static HashMap<Long, Course > courseMap = new HashMap<>();
	
	public List<Course> getAllCourses() {
		List<Course> coursesList = new ArrayList<>();
		
		for(Course course: courseMap.values()) {
			coursesList.add(course);
		}
		return coursesList;
	}
	
	
	public static Course courseAdding(Course course) {
		long courseId = (long) (courseMap.size() + 1);
		courseMap.put(courseId, course);
		
		return course;
	}
	
	public static Course getCourseById(long courseId) {
		return courseMap.get(courseId);
	}
	
	public static Course courseDeleting(long courseId) {
		Course deleteCourse  = courseMap.get(courseId);
		
		if(deleteCourse != null){
			courseMap.remove(courseId);
			return deleteCourse;
		}else {
			System.out.println("cannot delete Null Course");
			return deleteCourse;
		}
	}
	
	public static Course courseUpdating(long courseId, Course newCourse) {
		Course oldCourse = courseMap.get(courseId);
		
		if(oldCourse != null && newCourse != null) {
			long oldId = oldCourse.getCourseId();
			newCourse.setCourseId(oldId);
			courseMap.put(oldId, newCourse);
			return newCourse;
		}else {
			return oldCourse;
		}
		
	}
	

}
