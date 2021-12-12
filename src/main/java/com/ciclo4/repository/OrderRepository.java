package com.ciclo4.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ciclo4.model.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

	/**
	 * Regresa uma lista de ordenes segun la zona pasada como argumento
	 * 
	 * @param zone
	 * @return List
	 */
	@Query("{'salesMan.zone': ?0}")
	List<Order> findByZone(String zone);

	/**
	 * Regresa una lista de ordenes dado una zona y status
	 * 
	 * @param zone
	 * @param status
	 * @return List
	 */
	@Query("{'salesMan.zone': ?0, 'status' : ?1}")
	List<Order> findByZoneAndStatus(String zone, String status);
}
