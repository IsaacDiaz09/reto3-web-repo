package com.ciclo4.rest_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ciclo4.model.Role;
import com.ciclo4.model.User;
import com.ciclo4.repository.RoleRepository;
import com.ciclo4.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Metodo para obtener todos los usuarios
	 *
	 * @return List
	 */
	@GetMapping("/all")
	public List<User> getAll() {
		return service.getAll();
	}
	
	/**
	 * Regresa un usuario por su id
	 * 
	 * @param id
	 * @return User
	 */
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		Optional<User> user = service.getUserById(id);

		if (service.getUserById(id).isPresent()) {
			return user.get();
		}
		return new User();
	}

	/**
	 * Metodo para crear un usuario
	 *
	 * @param request
	 * @return User
	 */
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user) {
		Optional<Role> role = roleRepository.findByName(user.getType());
		if (role.isPresent()) {
			user.setRole(role.get());
			return service.newUser(user);
		}
		return user;
	}

	/**
	 * Metodo para verificar si existe un usuario con el Email ingresado
	 *
	 * @param email
	 * @return boolean
	 */
	@GetMapping("/emailexist/{correoElectronico}")
	public boolean byEmail(@PathVariable("correoElectronico") String email) {
		return service.verifyEmail(email);
	}

	/**
	 * Metodo para verificar si existe un usuario, Email y Contraseña
	 *
	 * @param email
	 * @param pass
	 * @return User
	 */
	@GetMapping("/{email}/{pass}")
	public User byEmailPass(@PathVariable("email") String email, @PathVariable("pass") String pass) {
		return service.byEmailPass(email, pass);
	}

	/**
	 * Metodo para actualizar un usuario
	 * 
	 * @param request
	 * @return User
	 */
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user) {
		Optional<Role> role = roleRepository.findByName(user.getType());
		if (role.isPresent()) {
			user.setRole(role.get());
			return service.editUser(user);
		}
		return user;
	}

	/**
	 * Metodo para eliminar un usuario
	 * 
	 * @param idUser
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer idUser) {
		service.deleteUser(idUser);
	}
}
