package com.tads.clientcrud.resource.exception;

import java.io.Serializable;
import java.time.Instant;

// Formato do JSON de erro devolvido ao Postman (ex.: no 404)
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Instant timestamp; // quando o erro aconteceu
	private Integer status;    // código HTTP (404)
	private String error;      // título do erro
	private String message;    // mensagem detalhada
	private String path;       // rota chamada

	public StandardError() {
	}

	public Instant getTimestamp() { return timestamp; }
	public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }

	public String getError() { return error; }
	public void setError(String error) { this.error = error; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }
}
