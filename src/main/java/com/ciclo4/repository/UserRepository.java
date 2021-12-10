package com.ciclo4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ciclo4.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	/**
	 * Metodo para encontrar por email
	 * 
	 * @param email
	 * @return Optional
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Recupera a todos los usuarios de una zona y tipo
	 * 
	 * @param email
	 * @param type
	 * @return List
	 */
	List<User> findAllByZoneAndType(String email, String type);

	/**
	 * Busca un asesor especifico por su Id
	 * 
	 * @param idASE
	 * @param string
	 * @return User
	 */
	Optional<User> findByIdAndType(Integer idASE, String string);
	
}
