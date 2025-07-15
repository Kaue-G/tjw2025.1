package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.meuprimeirospringboot.dto.RegistrationDTO;

public interface RegistrationService {
	Optional<RegistrationDTO> findById(Long id);
	List<RegistrationDTO> findAll();
	List<RegistrationDTO> findByClass(Long classId);
	List<RegistrationDTO> findByStudent(Long stdentId);
	
	void save(RegistrationDTO registrationDTO);
	void deactivateRegistration(Long id);
	
}
