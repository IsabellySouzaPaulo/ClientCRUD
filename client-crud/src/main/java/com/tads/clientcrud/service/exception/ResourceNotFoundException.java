package com.tads.clientcrud.service.exception;

// Exceção lançada quando um cliente não é encontrado
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
