package co.edu.icesi.dev.saamfi.logic.exceptions;

public class BadCodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadCodeException(String message) {
		super(message);
	}
	public BadCodeException() {
		super("El codigo no es igual al codigo enviado al email");
	}

}
