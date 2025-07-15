package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.enums.RoleName;

public interface UserRepository extends JpaRepository<User,Long > {

	 Optional<User> findByCpf(String cpf);	
	 Optional<User> findByEmail(String email);
	 
	 @Query("SELECT user FROM User user JOIN user.roles role WHERE role.name = :roleName")
	 List<User> findByRoleName(@Param("roleName") RoleName roleName);
}
