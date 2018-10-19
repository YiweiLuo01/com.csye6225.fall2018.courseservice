package com.csye6225.fall2018.courseservice.resources;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.csye6225.fall2018.courseservice.datamodel.Student;
import com.csye6225.fall2018.courseservice.service.StudentService;



@Path("/students")
public class StudentResource {

	StudentService studentService = new StudentService();
	

	
//	@GET
//	@Path("/{courseId}")
//	public List<Student> getStudentsByCourseId(@PathParam("courseId") Long courseId) {
//		
//		if (courseId == null) {
//			return null;
//		}
//		return StudentService.getStudentsByCourse(courseId);
//		
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudent() {
		return StudentService.getAllStudents();
	}
	
	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentById(@PathParam("studentId") long studentId) {
		return StudentService.getStudentById(studentId);
	}
	
	
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") long studentId) {
		return StudentService.studentDeleting(studentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student studentAdding(Student newStudent) {
		
		StudentService.studentAdding(newStudent);
		return newStudent;
	}
	
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Student updateStudentById(@PathParam("studentId") long studentId, 
			Student student) {
		return StudentService.studentUpdating(studentId, student);
	}
	
	
}
