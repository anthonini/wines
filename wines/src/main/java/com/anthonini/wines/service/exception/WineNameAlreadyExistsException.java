package com.anthonini.wines.service.exception;

public class WineNameAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WineNameAlreadyExistsException(String message) {
		super(message);
	}
}
