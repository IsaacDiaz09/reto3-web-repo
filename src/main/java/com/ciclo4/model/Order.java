package com.ciclo4.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

	public static final String PENDING = "Pendiente";
	public static final String APROVED = "Aprobada";
	public static final String REJECTED = "Rechazada";

	@Id
	private Integer id;

	private Date registerDay;

	private String status;

	private User salesMan;

	private Map<Integer, Gadget> products;

	private Map<Integer, Integer> quantities;

}
