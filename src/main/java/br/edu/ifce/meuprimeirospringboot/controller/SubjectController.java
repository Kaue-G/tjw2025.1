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

import br.edu.ifce.meuprimeirospringboot.dto.SubjectDTO;
import br.edu.ifce.meuprimeirospringboot.service.SubjectService;

@Controller
@RequestMapping("/admin/disciplinas")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("")
    public String listSubject(Model model) {
		List<SubjectDTO> subjects = subjectService.findAll(); 
		
		model.addAttribute("subjects", subjects);
		
		return "admin/subject-list";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute SubjectDTO subjectDTO) {
		subjectService.save(subjectDTO);
		
		return "redirect:/admin/disciplinas";
	}
	
	@GetMapping("/create")
	public String form(Model model) {
		SubjectDTO subject = new SubjectDTO();
		
		model.addAttribute("subject", subject);
		
		return "admin/subject-form";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model){
		SubjectDTO subject = subjectService.findById(id);
		
		 model.addAttribute("subject", subject);
		
        return "admin/subject-form";	
	}
	
	@GetMapping("/excluir/{id}")
    public String delete(@PathVariable Long id) {
		subjectService.deleteById(id);
		
		return "redirect:/admin/disciplinas";
	}
}
