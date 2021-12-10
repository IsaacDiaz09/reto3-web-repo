package com.ciclo4.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ciclo4.model.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {
    @Query("{'salesMan.zone': ?0}")
    List<Order> findByZone(final String zone);
}
