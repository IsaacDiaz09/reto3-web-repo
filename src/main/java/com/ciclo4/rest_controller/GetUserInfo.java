package com.ciclo4.rest_controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ciclo4.exception.BaseCustomException;
import com.ciclo4.security.CustomUserDetails;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class GetUserInfo {

	@GetMapping("/get_user_info")
	public Map<String, Object> getInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {

		try {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("name", userDetails.getName());
			map.put("email", userDetails.getUsername());
			map.put("zone", userDetails.getZone());
			map.put("role", userDetails.getRole());
			return map;

		} catch (NullPointerException e) {
			throw new BaseCustomException("No se pudo obtener la informacion del usuario", HttpStatus.INTERNAL_SERVER_ERROR.value());

		}
	}
}
