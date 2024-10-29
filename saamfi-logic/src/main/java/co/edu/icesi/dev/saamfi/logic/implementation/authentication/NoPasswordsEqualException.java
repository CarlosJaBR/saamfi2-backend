package co.edu.icesi.dev.saamfi.logic.implementation.authentication;

public class NoPasswordsEqualException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public NoPasswordsEqualException(String message) {
		super(message);
	}
	public NoPasswordsEqualException() {
		super("Las contraseñas no coinciden");
	}

}
