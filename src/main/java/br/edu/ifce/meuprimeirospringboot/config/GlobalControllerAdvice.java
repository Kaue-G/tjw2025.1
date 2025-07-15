package br.edu.ifce.meuprimeirospringboot.config;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.ifce.meuprimeirospringboot.util.SecurityUtils;

@ControllerAdvice
public class GlobalControllerAdvice {
	@ModelAttribute
	public void addGlobalAttributes(Model model) {
		String username = SecurityUtils.getCurrentUsername();
		List<String> roles = SecurityUtils.getCurrentUserRoles();
		
		model.addAttribute("username", username);
		model.addAttribute("roles", roles);
	}
}
