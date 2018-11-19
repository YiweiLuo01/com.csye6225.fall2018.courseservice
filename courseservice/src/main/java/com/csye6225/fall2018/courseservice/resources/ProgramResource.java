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

import com.csye6225.fall2018.courseservice.datamodel.Program;
import com.csye6225.fall2018.courseservice.service.ProgramService;



//.. /webapi/myresource
@Path("/programs")
public class ProgramResource {
	ProgramService programService = new ProgramService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getprograms() {
		return programService.getAllProgrames();
	}

	// ... webapi/program/
	@GET
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") String programId) {
		return ProgramService.getProgramById(programId);
	}

	@DELETE
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId") String programId) {
		return ProgramService.programDeleting(programId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program) {
		return ProgramService.programAdding(program);
	}

	@PUT
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") String programId, Program program) {
		return ProgramService.programUpdating(programId, program);
	}

}