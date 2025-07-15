package br.edu.ifce.meuprimeirospringboot.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long>{

}
