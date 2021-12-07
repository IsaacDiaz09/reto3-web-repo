package com.ciclo4.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({ "/", "/login" })
	public String index() {
		/**
		 * Verifica que el usuario no haya inicado sesion para que le envie al
		 * login,sino, directamente dentro a la aplicacion
		 */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "vistas/login";
		}
		return "redirect:/app";
	}
	
	@GetMapping("/app")
	public String loggedIn() {
		return "vistas/home";
	}
	
	@GetMapping("/forbidden")
	public String forbiddenPage() {
		return "error/forbidden";
	}
}
