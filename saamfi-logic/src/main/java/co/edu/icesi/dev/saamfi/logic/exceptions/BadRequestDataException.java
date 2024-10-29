package co.edu.icesi.dev.saamfi.logic.exceptions;

public class BadRequestDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public BadRequestDataException(String message) {
		super(message);
	}
	public BadRequestDataException() {
		super("Los datos de entrada de la solicitud no son validos");
	}
}
