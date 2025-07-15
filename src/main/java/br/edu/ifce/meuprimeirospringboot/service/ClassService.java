package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;

import br.edu.ifce.meuprimeirospringboot.dto.ClassDTO;

public interface ClassService {
	
    List<ClassDTO> findAll();
    ClassDTO findById(Long id);

    void save(ClassDTO classDTO);
    void deleteById(Long id);
}
