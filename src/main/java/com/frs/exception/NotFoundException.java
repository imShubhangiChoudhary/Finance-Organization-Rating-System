package com.frs.exception;

/**
 * Exception class for throwing NotFoundException which extends RuntimeException
 * class
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 *
 */

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	/**
	 * Default constructor 
	 * 
	 */
	
	public NotFoundException() {

	}

	/**
	 * Parameterized constructor 
	 * @param message
	 * 
	 */
	
	public NotFoundException(String message) {
		super(message);
	}
}