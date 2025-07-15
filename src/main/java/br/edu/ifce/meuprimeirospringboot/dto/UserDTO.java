package br.edu.ifce.meuprimeirospringboot.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.edu.ifce.meuprimeirospringboot.enums.Ethnicity;
import br.edu.ifce.meuprimeirospringboot.enums.RoleName;

public class UserDTO {
	private Long id;
    private String name;
    private String email;
	private String cpf;
    private Date dtBirth;
    private Ethnicity ethnicity;
    private Set<RoleName> roles = new HashSet<>();
    private String password;
    
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
	public Set<RoleName> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleName> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
