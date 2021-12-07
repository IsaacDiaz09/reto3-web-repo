package com.ciclo4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// Necesario para evitar que la seguridad se aplique a los recursos estaticos
	// Como los css, imagenes y archivos Js
	String[] resources = new String[] { "/css/**", "/icons/**", "/img/**", "/js/**", "/webjars/**", };

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	/**
	 * Se define este bean para no encriptar la contraseña y que no fallen los test
	 * mastertech -> Su uso esta desaconsejado y obsoleto.
	 * 
	 * @return NoOpPasswordEncoder
	 */
	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
.authorizeRequests().antMatchers(resources).permitAll()
				.antMatchers("/","/login", "/error", "/api/**").permitAll()
				.antMatchers("/app/users/update").hasAuthority("ADM")
				.antMatchers("/app/users/delete").hasAuthority("ADM")
				.antMatchers("/app/users/add").hasAuthority("ADM")
                                .antMatchers("/app/gadgets/add").hasAuthority("ADM")
                                .antMatchers("/app/gadgets/update").hasAuthority("ADM")
                                .antMatchers("/app/gadgets/delete").hasAuthority("ADM")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/app")
				.failureUrl("/login?error=true").and().logout().permitAll().logoutSuccessUrl("/login?logout")
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/forbidden");

		http.cors().and().csrf().disable();
	}
	
}
