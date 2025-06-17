package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	
	@GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
		
		// Obtendo o status HTTP da requisição
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        // Obtendo a mensagem de erro, se presente
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        
		return "error";  // Nome do arquivo .html no diretório /templates
    }

	public String getErrorPath() {
        return "/error";
    }
}
