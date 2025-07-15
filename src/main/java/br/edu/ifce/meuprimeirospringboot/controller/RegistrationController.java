package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.dto.RegistrationDTO;
import br.edu.ifce.meuprimeirospringboot.enums.RoleName;
import br.edu.ifce.meuprimeirospringboot.service.ClassService;
import br.edu.ifce.meuprimeirospringboot.service.RegistrationService;
import br.edu.ifce.meuprimeirospringboot.service.UserService;

@Controller
@RequestMapping("/admin/matriculas")
public class RegistrationController {

	@Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;
    
    @GetMapping("")
    public String list(Model model) {
        
    	List<RegistrationDTO> registrations = registrationService.findAll();
        
    	model.addAttribute("registrations", registrations);
        
    	return "admin/registration-list";
    }
    
    @GetMapping("/create")
    public String form(Model model) {
       
    	model.addAttribute("registration", new RegistrationDTO());
        model.addAttribute("students", userService.findByRole(RoleName.ROLE_USER));
        model.addAttribute("classes", classService.findAll());
        
        return "admin/registration-form";
    }
    
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("registration") RegistrationDTO dto) {
        
    	registrationService.save(dto);
        
        return "redirect:/admin/matriculas";
    }
    
    @GetMapping("/editar/{id}")
    public String edit(@PathVariable Long id, Model model) {
        RegistrationDTO registration = registrationService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        
        model.addAttribute("registration", registration);
        model.addAttribute("students", userService.findByRole(RoleName.ROLE_USER));
        model.addAttribute("classes", classService.findAll());
        
        return "admin/registration-form";
    }
    
    @GetMapping("/cancelar/{id}")
    public String cancel(@PathVariable Long id) {
        registrationService.deactivateRegistration(id);

        return "redirect:/admin/matriculas";
    }
    
}
