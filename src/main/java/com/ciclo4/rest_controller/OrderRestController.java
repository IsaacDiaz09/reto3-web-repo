package com.ciclo4.rest_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciclo4.model.Order;
import com.ciclo4.repository.OrderRepository;
import com.ciclo4.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

	@Autowired
	private OrderServiceImpl service;

	@Autowired
	OrderRepository repo;

	@GetMapping("/all")
	public List<Order> getAll() {
		return service.getAll();
	}

	@GetMapping("/remove__all")
	public void removeAll() {
		repo.deleteAll();
	}

	@GetMapping("/{id}")
	public Order getById(@PathVariable("id") Integer id) {
		Optional<Order> orderById = service.getById(id);
		if (orderById.isPresent()) {
			return orderById.get();
		}
		return new Order();
	}

	@PostMapping("/new")
	public Order newOrder(@RequestBody Order order) {
		return service.createOrder(order);
	}

	@PutMapping("/update")
	public Order updateOrder(@RequestBody Order order) {
		return service.updateOrder(order);
	}

}
