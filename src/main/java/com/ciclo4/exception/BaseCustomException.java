package com.ciclo4.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCustomException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7721727486457831781L;

	/**
	 * Atributo ErrrorCode
	 */
	private final int errorCode;

	/**
	 * Método constructor
	 *
	 * @param message
	 * @param errorCode
	 */
	public BaseCustomException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
