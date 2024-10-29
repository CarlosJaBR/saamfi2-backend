package co.edu.icesi.dev.saamfi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaamfiAppException extends RuntimeException {
	public SaamfiAppException(String message) {
		super(message);
	}

	public SaamfiAppException(String message, Throwable cause) {
		super(message, cause);
	}
}