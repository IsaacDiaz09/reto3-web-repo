package com.ciclo4.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclo4.model.Order;
import com.ciclo4.model.User;
import com.ciclo4.repository.OrderRepository;
import com.ciclo4.repository.UserRepository;

@Service
public class UtilsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Verifica si un ASE esta asociado a un COORD por su zona
	 * 
	 * @param idASE
	 * @param zone
	 * @return boolean
	 * @throws NoSuchElementException
	 */
	public boolean aseHasCoordByZone(Integer idASE, String zone) throws NoSuchElementException {
		User ase = userRepository.findByIdAndType(idASE, "ASE").get();
		List<User> coords = userRepository.findAllByZoneAndType(zone, "COORD");

		for (User coord : coords) {
			if (coord.getZone().equals(ase.getZone())) {
				return true;
			}
		}
		return false;
	}

	public List<Order> getAseOrders(String zone) {
		return orderRepository.findByZone(zone);
	}

	/**
	 * Se busca el id de orden mas alto para evitar conflictos y se regresa
	 * 
	 * @param idASE
	 * @return String
	 * @throws NoSuchElementException
	 */
	public Integer getAutogeneratedOrderId(Integer idASE) throws NoSuchElementException {
		if (userRepository.findByIdAndType(idASE, "ASE").isPresent()) {
			List<Integer> idsOrders = new ArrayList<Integer>();
			List<Order> orders = orderRepository.findAll();

			if (orders.isEmpty()) {
				return 1;
			}

			for (Order order : orderRepository.findAll()) {
				idsOrders.add(order.getId());
			}

			Integer nextId = Collections.max(idsOrders) + 1;
			return nextId;
		}
		throw new NoSuchElementException();
	}

}
