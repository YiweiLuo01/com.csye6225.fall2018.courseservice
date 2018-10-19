package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {
	
	static HashMap<Long, Professor> professor_map = InMemoryDatabase.getProfessorDB();
	
	
	
	public List<Professor> getAllProfessors() {	
		
		
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		
		for (Professor prof : professor_map.values()) {
			professorList.add(prof);
		}
		return professorList ;
	}

	
	public void professorAdding(String firstName, String lastName, String department, Date joiningDate) {
		
		
		long settingId = professor_map.size() + 1;
		
		
		Professor proffessor = new Professor(settingId, firstName, lastName , 
				department, joiningDate);
		
		professor_map.put(settingId, proffessor);
	}
	
	public Professor addProfessor(Professor prof) {	
		
		long addingId = professor_map.size() + 1;
		
		prof.setProfessorId(addingId);
		
		professor_map.put(addingId, prof);
		
		return professor_map.get(addingId);
	}
	
	
	public Professor getProfessorById(Long professorId) {
		return professor_map.get(professorId);
	}
	
	
	public Professor professorDeleting(Long professorId) {
		
		Professor deletingProf = professor_map.get(professorId);
		
		professor_map.remove(professorId);
		
		return deletingProf;
	}
	
	
	public Professor professorUpdating(Long profId, Professor newProfessor) {	
		
		Professor oldProf = professor_map.get(profId);
		
		profId = oldProf.getProfessorId();
		
		newProfessor.setProfessorId(profId);
		
		
		professor_map.put(profId, newProfessor) ;
		
		return newProfessor;
	}
	
	
	public List<Professor> getProfessorsByDepartment(String department) {	
	
		
		ArrayList<Professor> professorslist = new ArrayList<Professor>();
		
		for (Professor professor : professor_map.values()) {
			
			if (professor.getDepartment().equals(department)) {
				professorslist.add(professor);
			}
		}
		return professorslist ;
	}
	

	
}
