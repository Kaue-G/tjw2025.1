package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	Optional<Subject> findByName(String name);
	//Optional<Subject> findById(Long id);
	
}
