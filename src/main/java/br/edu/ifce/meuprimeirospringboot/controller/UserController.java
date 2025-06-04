package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifce.meuprimeirospringboot.beans.User;
import br.edu.ifce.meuprimeirospringboot.dto.CpfDTO;
import br.edu.ifce.meuprimeirospringboot.dto.UserDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Ethnicity;
import br.edu.ifce.meuprimeirospringboot.service.UserService;
import br.edu.ifce.meuprimeirospringboot.serviceImpl.UserServiceImpl;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	public UserController(UserService userService) {
        this.userService = (UserServiceImpl) userService;
    }
	
	// @RequestParam --> GET /usuario?cpf=12345678900
	// @RequestBody --> GET /usuario/12345678900 @GetMapping("/usuario/{cpf}")
	// @PathVariable --> Lê o corpo da requisição (normalmente JSON) e mapeia para um objeto Java.
	
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
	
	@GetMapping
    public String listUsers(Model model) {
		List<UserDTO> users = userService.findAll();
		
		model.addAttribute("users", users);
        return "admin/user-list";
    }
	
	@GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        UserDTO user = id != null ? userService.findById(id) : new UserDTO();
        model.addAttribute("user", user);
        model.addAttribute("ethnicities", Ethnicity.values());
        return "user/form";
    }
	
	@GetMapping("/delete")
    public String delete(@RequestParam Long id) {
		userService.deleteById(id);
		
        return "redirect:/admin/users";
    }

}
