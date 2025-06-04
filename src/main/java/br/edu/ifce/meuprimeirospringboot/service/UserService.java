package br.edu.ifce.meuprimeirospringboot.service;


import java.util.List;

import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.dto.UserDTO;

public interface UserService {
	User findByCPF(String cpf);
	User edit(Long id, User updatedUser);
	
	List<UserDTO> findAll();
    UserDTO findById(Long id);
    void save(UserDTO userDto);
    void deleteById(Long id);
}
