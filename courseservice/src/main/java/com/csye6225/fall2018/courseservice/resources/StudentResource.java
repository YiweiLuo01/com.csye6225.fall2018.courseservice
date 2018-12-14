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

import com.csye6225.fall2018.courseservice.datamodel.Course;
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
	public Student getStudentById(@PathParam("studentId") String studentId) {
		System.out.println("studentId: " + studentId);
		return StudentService.getStudentById(studentId);
	}

	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") String studentId) {
		return StudentService.studentDeleting(studentId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student studentAdding(Student newStudent) {

		StudentService.studentAdding(newStudent);
		return newStudent;
	}
	
	@POST
 	@Path("/{studentId}/{courseId}")
 	@Produces(MediaType.APPLICATION_JSON)
 	@Consumes(MediaType.APPLICATION_JSON)
 	public Student registerCourse(@PathParam("studentId") String studentId, @PathParam("courseId") String courseId) {
 			return studentService.registerCourse(studentId, courseId);
 	}

	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public Student updateStudentById(@PathParam("studentId") String studentId, Student student) {
		return StudentService.studentUpdating(studentId, student);
	}

	
	
}
