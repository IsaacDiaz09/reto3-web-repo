package com.ciclo4.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ciclo4.exception.BaseCustomException;
import com.ciclo4.model.Role;
import com.ciclo4.model.User;
import com.ciclo4.repository.RoleRepository;
import com.ciclo4.repository.UserRepository;

/**
 * @author CarlinGebyte
 */
@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Método para obtener todos los usuarios
	 *
	 * @return
	 */
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<Role> getRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/**
	 * Método para crear un usuario
	 *
	 * @param user
	 * @return User
	 */
	public User newUser(User user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(e -> {
			throw new BaseCustomException("El correo ya existe", HttpStatus.BAD_REQUEST.value());
		});

		User savedUser = userRepository.save(user);

		return savedUser;
	}

	/**
	 * Método para verificar si existe un usuario con el Email ingresado
	 *
	 * @param email
	 * @return boolean
	 */
	public boolean verifyEmail(String email) {
		List<User> users = userRepository.findAll();
		boolean flag = false;
		for (User user : users) {
			if (email.equals(user.getEmail())) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * Método para verificar si existe un usuario, Email y Contraseña
	 *
	 * @param email
	 * @param pass
	 * @return User
	 */
	public User byEmailPass(String email, String pass) {
		List<User> users = getAll();
		User notExist = new User();
		for (User user : users) {
			if (email.equals(user.getEmail()) && pass.equals(user.getPassword())) {
				return user;
			}
		}
		return notExist;
	}

	/**
	 * Método para actualizar un usuario
	 * 
	 * @param user
	 * @return User
	 */
	public User editUser(User user) {
		if (user.getId() != null) {
			Optional<User> exist = userRepository.findById(user.getId());
			if (exist.isPresent()) {
				if (user.getName() != null) {
					exist.get().setName(user.getName());
				}
				if (user.getIdentification() != null) {
					exist.get().setIdentification(user.getIdentification());
				}
				if (user.getEmail() != null) {
					exist.get().setEmail(user.getEmail());
				}
				if (user.getAddress() != null) {
					exist.get().setAddress(user.getAddress());
				}
				if (user.getCellPhone() != null) {
					exist.get().setCellPhone(user.getCellPhone());
				}
				if (user.getPassword() != null) {
					exist.get().setPassword(user.getPassword());
				}
				if (user.getZone() != null) {
					exist.get().setZone(user.getZone());
				}
				if (user.getType()!= null) {
					exist.get().setType(user.getType());
				}
				return userRepository.save(exist.get());
			}
		}
		return new User();
	}

	/**
	 * borra un usuario si existe
	 * 
	 * @param idUser
	 */
	public void deleteUser(Integer idUser) {
		Optional<User> user = userRepository.findById(idUser);
		if (user.isPresent()) {
			userRepository.deleteById(idUser);
		}
	}

	/**
	 * regresa un usuario por id si lo encuentra
	 * 
	 * @param idUser
	 * @return
	 */
	public Optional<User> getUserById(Integer idUser) {
		return userRepository.findById(idUser);
	}

	/**
	 * regresa los roles disponibles de n usuario
	 * 
	 * @return Map
	 */
	public Map<String, String> getUserRoles() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("COORD", "Coordinador de zona");
		map.put("ASE", "Asesor comercial");
		map.put("ADM", "Administrador");
		return map;
	}
}
