package com.ciclo4.model;

import java.util.Date;

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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "usuarios")
@JsonIgnoreProperties("role")
public class User {
	@Id
	private Integer id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

	private String identification;
	
	private String name;
	
	private Date birthtDay;

	private String monthBirthtDay;
	
	private String address;
	
	private String cellPhone;
	
	private String email;
	
	private String password;
	
	private String zone;
	
	private String type;

	@DBRef
	private Role role;
}
