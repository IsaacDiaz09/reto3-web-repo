package com.ciclo4.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author CarlinGebyte
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "usuarios")
@JsonIgnoreProperties("role")
public class User {
	/**
	 * Atributo ID
	 */
	@Id
	private Integer id;
    @Indexed(unique=true,direction = IndexDirection.DESCENDING,dropDups=true)
	/**
	 * Atributo identificación
	 */
	private String identification;
    /**
	 * Atributo Name
	 */
	private String name;
	/**
	 * Atributo dirección
	 */
	private String address;
	/**
	 * Atributo teléfono
	 */
	private String cellPhone;
	/**
	 * Atributo Email
	 */
	private String email;
	/**
	 * Atributo Password
	 */
	private String password;
	/**
	 * Atributo zone
	 */
	private String zone;
	/**
	 * Atributo type
	 */
	private String type;
	
	@DBRef
	private Role role;
}
