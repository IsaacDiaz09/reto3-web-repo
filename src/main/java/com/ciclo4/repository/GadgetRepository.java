package com.ciclo4.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ciclo4.model.Gadget;

@Repository
public interface GadgetRepository extends MongoRepository<Gadget, Integer> {
}
