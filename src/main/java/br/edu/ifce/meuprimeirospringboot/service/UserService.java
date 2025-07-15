package br.edu.ifce.meuprimeirospringboot.service;


import java.util.List;

import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.dto.UserDTO;
import br.edu.ifce.meuprimeirospringboot.enums.RoleName;

public interface UserService {
	
	List<UserDTO> findByRole(RoleName roleName);
	User findByCPF(String cpf);
	User edit(Long id, User updatedUser);
	UserDTO findById(Long id);
	
	List<UserDTO> findAll();
    void save(UserDTO userDto);
    void deleteById(Long id);
}
