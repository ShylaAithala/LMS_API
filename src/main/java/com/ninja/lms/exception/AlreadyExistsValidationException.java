package com.ninja.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExistsValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7179488439144347806L;

	public AlreadyExistsValidationException(String exception) {
		// TODO Auto-generated constructor stub
		super(exception);
	}

}
