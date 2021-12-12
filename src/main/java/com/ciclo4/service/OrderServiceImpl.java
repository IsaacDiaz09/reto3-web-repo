package com.ciclo4.service;

import java.util.Date;
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
		Optional<Order> orderExists = repo.findById(order.getId());
		if (orderExists.isEmpty() && (!Objects.isNull(order.getSalesMan()) && !Objects.isNull(order.getProducts())
				&& !Objects.isNull(order.getQuantities()))) {

			order.setStatus(Order.PENDING);

			if (Objects.isNull(order.getRegisterDay())) {
				order.setRegisterDay(new Date());
			}

			return repo.save(order);
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
				String status = order.getStatus();

				Order orderToUpdate = orderExists.get();
				if (!Objects.isNull(order.getRegisterDay())) {
					orderToUpdate.setRegisterDay(order.getRegisterDay());
				}
				if (!Objects.isNull(status) && (status.equals(Order.APROVED) || status.equals(Order.PENDING)
						|| status.equals(Order.REJECTED))) {
					orderToUpdate.setStatus(order.getStatus());
				}
				if (!Objects.isNull(order.getSalesMan())) {
					orderToUpdate.setSalesMan(order.getSalesMan());

				}
				if (!Objects.isNull(order.getRegisterDay())) {
					orderToUpdate.setRegisterDay(order.getRegisterDay());
				}
				if (!Objects.isNull(order.getProducts())) {
					orderToUpdate.setProducts(order.getProducts());

				}
				if (!Objects.isNull(order.getQuantities())) {
					orderToUpdate.setQuantities(order.getQuantities());

				}

				return repo.save(orderToUpdate);
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

	/**
	 * Regreasa las ordenes de una zona
	 * 
	 * @param zone
	 * @return List
	 */
	public List<Order> getAseOrders(String zone) {
		return repo.findByZone(zone);
	}

	/**
	 * regresa la ordenes con una zona y estado dados
	 * 
	 * @param zone
	 * @param status
	 * @return List
	 */
	public List<Order> getOrdersByStatusAndZone(String zone,String status) {
		return repo.findByZoneAndStatus(zone,status);
	}
}
