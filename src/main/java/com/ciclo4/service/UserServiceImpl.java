package com.ciclo4.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ciclo4.exception.BaseCustomException;
import com.ciclo4.model.Role;
import com.ciclo4.model.User;
import com.ciclo4.repository.RoleRepository;
import com.ciclo4.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Metodo para obtener todos los usuarios
	 *
	 * @return List
	 */
	public List<User> getAll() {
		return userRepository.findAll();
	}

	/**
	 * Regreasa rol por su nombre
	 * 
	 * @param name
	 * @return Optional
	 */
	public Optional<Role> getRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	/**
	 * Metodo para crear un usuario
	 *
	 * @param user
	 * @return User
	 */
	public User newUser(User user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(e -> {
			throw new BaseCustomException("El correo ya existe", HttpStatus.BAD_REQUEST.value());
		});

		if (!Objects.isNull(user.getId()) && !Objects.isNull(user.getIdentification())
				&& !Objects.isNull(user.getName()) && !Objects.isNull(user.getBirthtDay())
				&& !Objects.isNull(user.getMonthBirthtDay()) && !Objects.isNull(user.getAddress())
				&& !Objects.isNull(user.getCellPhone()) && !Objects.isNull(user.getEmail())
				&& !Objects.isNull(user.getPassword()) && !Objects.isNull(user.getZone())
				&& !Objects.isNull(user.getType())) {

			return userRepository.save(user);
		}
		return user;

	}

	/**
	 * Metodo para verificar si existe un usuario con el Email ingresado
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
	 * Metodo para verificar si existe un usuario, Email y Contraseña
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
	 * Metodo para actualizar un usuario
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
				if (user.getType() != null) {
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
	 * @return Optional
	 */
	public Optional<User> getUserById(Integer idUser) {
		return userRepository.findById(idUser);
	}

	/**
	 * Verifica si un ASE esta asociado a un COORD por su zona
	 * 
	 * @param zone
	 * @return boolean
	 */
	public boolean aseHasCoordByZone(Integer idASE, String zone) throws NoSuchElementException {
		User ase = userRepository.findByIdAndType(idASE,"ASE").get();
		List<User> coords = userRepository.findAllByZoneAndType(zone, "COORD");

		for (User coord : coords) {
			if (coord.getZone().equals(ase.getZone())) {
				return true;
			}
		}
		return false;
	}

}
