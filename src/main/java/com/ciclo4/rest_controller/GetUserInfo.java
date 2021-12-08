package com.ciclo4.rest_controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ciclo4.exception.BaseCustomException;
import com.ciclo4.security.CustomUserDetails;
import com.ciclo4.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public class GetUserInfo {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/get_user_info")
	public Map<String, Object> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {

		try {
			return userDetails.getDetails();
		} catch (NullPointerException e) {
			throw new BaseCustomException("No se pudo obtener la informacion del usuario",
					HttpStatus.INTERNAL_SERVER_ERROR.value());

		}
	}

	@GetMapping("/get_user_zone")
	public Map<String, String> getUserZone(@AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			return userDetails.getZone();
		} catch (NullPointerException e) {
			throw new BaseCustomException("No se pudo obtener la zona del usuario",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	@GetMapping("/ase_has_coord_by_zone")
	public boolean aseIsAbleToMakeOrders(@AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			return userService.aseHasCoordByZone(userDetails.getId(), userDetails.getZone().get("zone"));
		} catch (NoSuchElementException e) {
			throw new BaseCustomException("No se pudo resolver la solicitud, puede que no seas un Asesor Comercial", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}
}
