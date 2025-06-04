package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.edu.ifce.meuprimeirospringboot.beans.User;

public interface UserRepository extends JpaRepository<User,Long > {

	 Optional<User> findByCpf(String cpf);	
	 Optional<User> findByEmail(String email);
}
