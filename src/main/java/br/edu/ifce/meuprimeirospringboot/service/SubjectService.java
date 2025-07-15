package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;

import br.edu.ifce.meuprimeirospringboot.dto.SubjectDTO;

public interface SubjectService {
	
	SubjectDTO findById(Long id);
	List<SubjectDTO> findAll();
	SubjectDTO edit(Long id, SubjectDTO updatedSubject);
	
	void save(SubjectDTO subjectDTO);
	void deleteById(Long id);
}
