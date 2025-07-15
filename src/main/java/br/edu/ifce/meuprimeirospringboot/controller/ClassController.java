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

import br.edu.ifce.meuprimeirospringboot.dto.ClassDTO;
import br.edu.ifce.meuprimeirospringboot.enums.RoleName;
import br.edu.ifce.meuprimeirospringboot.service.ClassService;
import br.edu.ifce.meuprimeirospringboot.service.SubjectService;
import br.edu.ifce.meuprimeirospringboot.service.UserService;

@Controller
@RequestMapping("/admin/turmas")
public class ClassController {

	@Autowired private ClassService classService;
	@Autowired private SubjectService subjectService;
	@Autowired private UserService userService;
	
	@GetMapping("")
    public String listClasses(Model model) {
        List<ClassDTO> classes = classService.findAll();
        
        model.addAttribute("classes", classes);
        
        return "admin/class-list";
    }
	
	@GetMapping("/create")
    public String newForm(Model model) {
        model.addAttribute("classDTO", new ClassDTO());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", userService.findByRole(RoleName.ROLE_TEACHER));
        
        return "admin/class-form";
    }
	
	@PostMapping("/salvar")
    public String salvar(@ModelAttribute ClassDTO classDTO, Model model) {
        classService.save(classDTO);
        
        return "redirect:/admin/turmas";
    }
	
	@GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ClassDTO classData = classService.findById(id);
        
        model.addAttribute("classDTO", classData);
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", userService.findByRole(RoleName.ROLE_TEACHER));
        
        return "admin/class-form";
    }
	
	@GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        classService.deleteById(id);

        return "redirect:/admin/turmas";
    }

}
