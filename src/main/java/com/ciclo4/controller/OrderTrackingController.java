package com.ciclo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/seguimiento")
public class OrderTrackingController {

	@GetMapping
	public String home_orders(Model model) {
		return "vistas/seguimiento/seguimiento";
	}
}
