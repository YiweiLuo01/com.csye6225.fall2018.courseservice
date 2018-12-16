package com.csye6225.fall2018.courseservice.resources;

import java.util.Date;
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

import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.service.ProfessorsService;

@Path("professors")
public class ProfessorsResource {

	ProfessorsService professorService = new ProfessorsService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessors() {
		return professorService.getAllProfessors();
	}

//	@GET
//	@Path("/{department}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Professor> getProfessorsByDeparment(@PathParam("department") String department) {
//		
//		if (department == null) {
//			return professorService.getAllProfessors();
//		}
//		return professorService.getProfessorsByDepartment(department);
//		
//	}

	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessorById(@PathParam("professorId") String profId) {
		return professorService.getProfessor(profId);
	}

	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") String profId) {
		return professorService.professorDeleting(profId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
		return professorService.addProfessor(prof);
	}

	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public Professor updateProfessor(@PathParam("professorId") String profId, Professor prof) {
		return professorService.professorUpdating(profId, prof);
	}

//	public void addProfessor(String firstName,String lastName,  String department, Date joiningDate) {
//		professorService.professorAdding(firstName, lastName, department, joiningDate);
//	}
}
