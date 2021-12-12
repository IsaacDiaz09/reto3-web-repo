package com.ciclo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ciclo4.model.Gadget;
import com.ciclo4.service.GadgetServiceImpl;

@Controller
@RequestMapping("/app/gadgets")
public class GadgetsController {

	@Autowired
	private GadgetServiceImpl service;

	@GetMapping
	public String gadgetsPage(Model model) {
		List<Gadget> gadgets = service.getAll();
		model.addAttribute("gadgets", gadgets);
		return "vistas/products/gadgets";
	}

	@GetMapping("/update")
	public String editar(Gadget gadget, Model model) {
		gadget = service.getGadgetById(gadget.getId());
		model.addAttribute("gadget", gadget);
		model.addAttribute("text_h3", "Editar producto");
		model.addAttribute("availability", service.getAvailability());
		return "vistas/products/update-gadget";
	}

	@GetMapping("/add")
	public String agregar(Gadget gadget, Model model) {
		model.addAttribute("gadget", gadget);
		model.addAttribute("text_h3", "Registrar producto");

		return "vistas/products/new-gadget";
	}

	@GetMapping("/delete")
	public String eliminar(Gadget gadget, Model model) {
		service.deleteGadget(gadget.getId());
		return "redirect:/app/gadgets";
	}
}
