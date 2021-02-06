package com.frs.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author G4
 * @version
 * @since 2020-2-5
 * 
 */

@ControllerAdvice
public class FRSException extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetails> handleException(NotFoundException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 * 
	 */

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * Handles the exceptions which are thrown due to validation of data
	 * 
	 */
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
