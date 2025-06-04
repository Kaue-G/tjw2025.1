package br.edu.ifce.meuprimeirospringboot.dto;

import java.util.Date;

import br.edu.ifce.meuprimeirospringboot.enums.Ethnicity;

public class UserDTO {
	private Long id;
    private String name;
    private String email;
	private String cpf;
    private Date dtBirth;
    private Ethnicity ethnicity;
    private String role;
    
    public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDtBirth() {
		return dtBirth;
	}
	public void setDtBirth(Date dtBirth) {
		this.dtBirth = dtBirth;
	}
	public Ethnicity getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
