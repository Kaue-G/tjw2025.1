package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
	@GetMapping("/login")
    public String loginPage() {
        return "login";  // Retorna o template login.html
    }

	@GetMapping("/home")
    public String home() {
        return "home";
    }
	
}
