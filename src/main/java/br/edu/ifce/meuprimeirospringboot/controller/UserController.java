package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Role;
import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.dto.CpfDTO;
import br.edu.ifce.meuprimeirospringboot.dto.UserDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Ethnicity;
import br.edu.ifce.meuprimeirospringboot.repository.RoleRepository;
import br.edu.ifce.meuprimeirospringboot.service.UserService;
import br.edu.ifce.meuprimeirospringboot.util.RoleNameMapper;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;
	
	// @RequestParam --> GET /usuario?cpf=12345678900
	// @RequestBody --> GET /usuario/12345678900 @GetMapping("/usuario/{cpf}")
	// @PathVariable --> Lê o corpo da requisição (normalmente JSON) e mapeia para um objeto Java.
	
	@GetMapping("")
    public String listUsers(Model model) {
		List<UserDTO> users = userService.findAll();
		
		model.addAttribute("users", users);
		
        return "admin/user-list";
    }
	
	@PostMapping("/buscar-por-cpf")
    public ResponseEntity<User> getUserByCPF(@RequestBody CpfDTO dto) {
		User user = userService.findByCPF(dto.getCpf());
	        return ResponseEntity.ok(user); 
	    }
	
	@PutMapping("/{id}")
	public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User updatedUser) {
		User editedUser = userService.edit(id, updatedUser);
		
	    return ResponseEntity.ok(editedUser);
	}
	
	@PostMapping("/save")
    public String save(@ModelAttribute UserDTO userDTO) {
		userService.save(userDTO);
		
        return "redirect:/admin/users";
    }
	
	@GetMapping("/create")
    public String form( Model model) {
        UserDTO user = new UserDTO(); 
        List<Role> roles = roleRepository.findAll();
        
        model.addAttribute("user", user);
        model.addAttribute("ethnicities", Ethnicity.values());
        model.addAttribute("allRoles", roles);
        model.addAttribute("roleNames", RoleNameMapper.getRoleDisplayNames()); 
        
        return "admin/form";
    }
	
	@GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        UserDTO user = userService.findById(id); 
        
        model.addAttribute("user", user);
        model.addAttribute("ethnicities", Ethnicity.values());
        model.addAttribute("allRoles", roleRepository.findAll());
        model.addAttribute("roleNames", RoleNameMapper.getRoleDisplayNames());
        
        return "admin/form";  
    }
	
	@PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
		userService.deleteById(id);
		
        return "redirect:/admin/users";
    }

}
