package com.ciclo4.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "gadgets")
public class Gadget {
	@Id
	private Integer id;

	private String brand;

	private String category;

	private String name;

	private String description;

	private Double price;

	private Boolean availability;

	private Integer quantity;

	private String photography;

}
