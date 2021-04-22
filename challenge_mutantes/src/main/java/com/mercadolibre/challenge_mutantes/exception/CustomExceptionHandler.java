package com.mercadolibre.challenge_mutantes.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Void> handleMethodArgumentNotValid(@NotNull HttpServletRequest httpServletRequest, 
			@NotNull Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}