package br.edu.ifce.meuprimeirospringboot.dto;

public class RegistrationDTO {
	
	private Long id;
	private Long studentId;
	private String studentName;
	
	private Long classId;
	private String registrationSubject;
	private String registrationTeacher;
	
	private String registrationDateFormatted;

	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getRegistrationSubject() {
		return registrationSubject;
	}

	public void setRegistrationSubject(String registrationSubject) {
		this.registrationSubject = registrationSubject;
	}

	public String getRegistrationTeacher() {
		return registrationTeacher;
	}

	public void setRegistrationTeacher(String registrationTeacher) {
		this.registrationTeacher = registrationTeacher;
	}

	public String getRegistrationDateFormatted() {
		return registrationDateFormatted;
	}

	public void setRegistrationDateFormatted(String registrationDateFormatted) {
		this.registrationDateFormatted = registrationDateFormatted;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
