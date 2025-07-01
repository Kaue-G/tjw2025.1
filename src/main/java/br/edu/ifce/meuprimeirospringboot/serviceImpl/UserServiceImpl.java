package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Role;
import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.dto.UserDTO;
import br.edu.ifce.meuprimeirospringboot.exceptions.UserNotFoundException;
import br.edu.ifce.meuprimeirospringboot.repository.RoleRepository;
import br.edu.ifce.meuprimeirospringboot.repository.UserRepository;
import br.edu.ifce.meuprimeirospringboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

	@Override
	public User findByCPF(String cpf) {
		return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException(cpf));
    }

	@Override
	public void save(UserDTO dto) {
		User user = dto.getId() != null 
				? userRepository.findById(dto.getId()).orElse(new User()) 
				: new User();
        
		user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCpf(dto.getCpf());
        user.setDtBirth(dto.getDtBirth());
        user.setEthnicity(dto.getEthnicity());
        
        Set<Role> roles = dto.getRoles().stream()
                .map(name -> roleRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Role não encontrada: " + name)))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        if (dto.getId() == null) {
            user.setPassword(encoder.encode("123456")); // senha padrão
        } else if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(encoder.encode(dto.getPassword()));
        }        

        userRepository.save(user);
	}

	@Override
	public User edit(Long id, User updatedUser) {
		User existente = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

	        existente.setName(updatedUser.getNome());
	        existente.setEmail(updatedUser.getEmail());
	        existente.setCpf(updatedUser.getCpf());
	        existente.setDtBirth(updatedUser.getDtBirth());
	        existente.setEthnicity(updatedUser.getEthnicity());
	        existente.setAddress(updatedUser.getAddress());
	        existente.setTelefones(updatedUser.getPhones());
	        existente.setSubjects(updatedUser.getSubjects());

	        return userRepository.save(existente);
	}
	
	@Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(this::toDTO).orElse(null);
    }
    
    @Override
    public void deleteById(Long id) {
    	userRepository.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setCpf(user.getCpf());
        dto.setDtBirth(user.getDtBirth());
        dto.setEthnicity(user.getEthnicity());
        
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        
        dto.setRoles(roleNames);
        
        return dto;
    }
}
