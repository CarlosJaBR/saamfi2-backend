package co.edu.icesi.dev.saamfi.logic.exceptions;

public class ExpirationCodeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExpirationCodeException(String message) {
		super(message);
	}
	public ExpirationCodeException() {
		super("El código de validación ya no es valido, se venció su tiempo de expiración");
	}

}
