package com.ciclo4.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ciclo4.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {

	/**
	 * Metodo para buscar por rol
	 * @param name
	 * @return Optional
	 */
	Optional<Role> findByName(String name);
	
}
