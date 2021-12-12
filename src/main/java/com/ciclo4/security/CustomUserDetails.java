package com.ciclo4.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ciclo4.model.User;

public class CustomUserDetails implements UserDetails {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1056271089079272597L;
	private User user;

	/**
	 * @param user
	 */
	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(user.getRole().getName()));
		return authority;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return user.getId();
	}

	public String getName() {
		return user.getName();
	}

	public String getRole() {
		return user.getRole().getDescription();
	}

	public String getZone() throws NullPointerException {
		return user.getZone();
	}

	public Map<String, Object> getDetails() throws NullPointerException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", user.getId());
		map.put("name", user.getName());
		map.put("email", user.getEmail());
		map.put("cellPhone", user.getCellPhone());
		map.put("address", user.getAddress());
		map.put("zone", user.getZone());
		map.put("role", user.getRole().getDescription());

		return map;
	}

}