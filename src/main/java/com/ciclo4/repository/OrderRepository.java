package com.ciclo4.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ciclo4.model.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {
	
}
