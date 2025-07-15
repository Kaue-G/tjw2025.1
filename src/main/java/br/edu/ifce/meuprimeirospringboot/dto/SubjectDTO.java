package br.edu.ifce.meuprimeirospringboot.dto;

public class SubjectDTO {

	private Long id;
	private String year;
	private String period;
	private String name;
	private String teacher;
	private int qtStudents;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getQtStudents() {
		return qtStudents;
	}
	public void setQtStudents(int qtStudents) {
		this.qtStudents = qtStudents;
	}
}
