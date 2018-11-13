package com.csye6225.fall2018.courseservice.datamodel;

public class Course {
	private String courseId;
	private String lectureId;
	private String board;
	private String roster;
	private String[] studentId;
	private String professorId;
	private String StudentTAId;
	
	
	public Course() {
		
	}
	public Course(String courseId, String lectureId, String board, String roster, String studentId, String professorId, String studentTAId) {
		
	}
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
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

	public String[] getStudentId() {
		return studentId;
	}

	public void setStudentId(String[] studentId) {
		this.studentId = studentId;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getStudentTAId() {
		return StudentTAId;
	}

	public void setStudentTAId(String StudentTAId) {
		this.StudentTAId = StudentTAId;
	}
	

}
