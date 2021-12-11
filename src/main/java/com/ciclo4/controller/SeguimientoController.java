package com.ciclo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ciclo4.model.Order;
import com.ciclo4.service.OrderServiceImpl;

@Controller
@RequestMapping("/app/seguimiento")
public class SeguimientoController {

	@Autowired
	private OrderServiceImpl service;

	@GetMapping
	public String home_orders(Model model) {
		List<Order> orders = service.getAll();
		model.addAttribute("orders", orders);
		return "vistas/seguimiento/seguimiento";
	}
}
