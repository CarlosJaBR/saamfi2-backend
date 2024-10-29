package co.edu.icesi.dev.saamfi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaamfiBadRequestException extends RuntimeException {

	public SaamfiBadRequestException(String message) {
		super(message);
	}

	public SaamfiBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}