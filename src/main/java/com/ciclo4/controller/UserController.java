package com.ciclo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ciclo4.model.User;
import com.ciclo4.service.UserServiceImpl;

@Controller
@RequestMapping("/app/users")
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@GetMapping
	public String usersPage(Model model) {
		List<User> users = service.getAll();

		model.addAttribute("users", users);
		return "vistas/users/users";
	}

	@GetMapping("/add")
	public String addUser(Model model) {
		model.addAttribute("roles", service.getUserRoles());
		model.addAttribute("text_h3", "Registrar usuario");
		model.addAttribute("user", new User());

		return "vistas/users/new-user";
	}

	@GetMapping("/update")
	public String redireccionActualizar(User user, Model model) {
		user = service.getUserById(user.getId()).get();
		model.addAttribute("user", user);
		model.addAttribute("text_h3", "Editar usuario");
		model.addAttribute("roles", service.getUserRoles());

		return "vistas/users/update-user";
	}

	@GetMapping("/delete")
	public String eliminar(User user, Model model) {
		service.deleteUser(user.getId());
		return "redirect:/app/users";
	}

}