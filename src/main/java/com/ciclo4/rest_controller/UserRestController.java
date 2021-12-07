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

/**
 * @author CarlinGebyte
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Método para obtener todos los usuarios
	 *
	 * @return
	 */
	@GetMapping("/all")
	public List<User> getAll() {
		return userServiceImpl.getAll();
	}

	/**
	 * Método para crear un usuario
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user) {
		Optional<Role> role = roleRepository.findByName(user.getType());
		if (role.isPresent()) {
			user.setRole(role.get());
			return userServiceImpl.newUser(user);
		}
		return user;
	}

	/**
	 * Método para verificar si existe un usuario con el Email ingresado
	 *
	 * @param email
	 * @return
	 */
	@GetMapping("/emailexist/{correoElectronico}")
	public boolean byEmail(@PathVariable("correoElectronico") String email) {
		return userServiceImpl.verifyEmail(email);
	}

	/**
	 * Método para verificar si existe un usuario, Email y Contraseña
	 *
	 * @param email
	 * @param pass
	 * @return
	 */
	@GetMapping("/{email}/{pass}")
	public User byEmailPass(@PathVariable("email") String email, @PathVariable("pass") String pass) {
		return userServiceImpl.byEmailPass(email, pass);
	}

	/**
	 * Método para actualizar un usuario
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user) {
		Optional<Role> role = roleRepository.findByName(user.getType());
		if (role.isPresent()) {
			user.setRole(role.get());
			return userServiceImpl.editUser(user);
		}
		return user;
	}

	/**
	 * Método para eliminar un usuario
	 * 
	 * @param idUser
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer idUser) {
		userServiceImpl.deleteUser(idUser);
	}
}
