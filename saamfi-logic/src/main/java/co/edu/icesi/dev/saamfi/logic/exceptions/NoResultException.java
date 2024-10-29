package co.edu.icesi.dev.saamfi.logic.exceptions;

public class NoResultException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NoResultException(String message) {
		super(message);
	}
	public NoResultException() {
		super("No se retornaron datos en la consulta");
	}

}
