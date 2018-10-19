package com.csye6225.fall2018.courseservice.datamodel;

public class Course {
	private long courseId;
	private long lectureId;
	private String board;
	private String roster;
	private long studentId;
	private long professorId;
	private long StudentTA;
	
	
	public Course() {
		
	}
	public Course(long courseId, long lectureId, String board, String roster, long studentId, long professorId, long studentTA) {
		
	}
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getRoster() {
		return roster;
	}

	public void setRoster(String roster) {
		this.roster = roster;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}

	public long getStudentTA() {
		return StudentTA;
	}

	public void setStudentTA(long studentTA) {
		StudentTA = studentTA;
	}
	

}
