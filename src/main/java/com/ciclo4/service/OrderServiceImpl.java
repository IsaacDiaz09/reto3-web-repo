package com.ciclo4.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclo4.model.Order;
import com.ciclo4.repository.OrderRepository;

@Service
public class OrderServiceImpl {

	@Autowired
	private OrderRepository repo;

	/**
	 * Regresa todas las ordenes
	 * 
	 * @return List
	 */
	public List<Order> getAll() {
		return repo.findAll();
	}

	/**
	 * Regresa una orden si la encuentra
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Order> getById(Integer id) {
		return repo.findById(id);
	}

	/**
	 * Agrega una nueva orden
	 * 
	 * @param order
	 * @return Order
	 */
	public Order createOrder(Order order) {
		if (!Objects.isNull(order.getId())) {
			Optional<Order> orderExists = repo.findById(order.getId());
			if (orderExists.isEmpty()) {
				return repo.save(order);
			}
		}
		return order;
	}

	/**
	 * Actualiza una orden con nuevos datos
	 * 
	 * @param order
	 * @return Order
	 */
	public Order updateOrder(Order order) {
		if (!Objects.isNull(order.getId())) {
			Optional<Order> orderExists = repo.findById(order.getId());
			if (orderExists.isPresent()) {
				return order;
			}
		}
		return order;
	}

	/**
	 * Borra una orden si existe
	 * 
	 * @param id
	 */
	public void deleteOrder(Integer id) {
		if (!Objects.isNull(id)) {
			Optional<Order> orderExists = repo.findById(id);
			if (orderExists.isEmpty()) {
				repo.deleteById(id);
			}
		}
	}

}
