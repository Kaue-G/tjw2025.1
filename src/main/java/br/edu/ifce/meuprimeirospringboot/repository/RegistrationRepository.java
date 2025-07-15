package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{

	List<Registration> findByStudentId(Long studentId);
	List<Registration> findByClassEntityId(Long classEntityId);
}
