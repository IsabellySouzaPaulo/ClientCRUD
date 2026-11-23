package com.tads.clientcrud.service.exception;

// Exceção para violação de integridade do banco (ex.: apagar algo que é referenciado em outra tabela).
public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg) {
		super(msg);
	}
}
