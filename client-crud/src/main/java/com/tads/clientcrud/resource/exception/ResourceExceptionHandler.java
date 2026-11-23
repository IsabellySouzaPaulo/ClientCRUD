package com.tads.clientcrud.resource.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tads.clientcrud.service.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

// Captura exceções lançadas em qualquer Resource e transforma em resposta HTTP
@ControllerAdvice
public class ResourceExceptionHandler {

	// Quando alguém lança ResourceNotFoundException, responde 404 com o JSON de StandardError
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
}
