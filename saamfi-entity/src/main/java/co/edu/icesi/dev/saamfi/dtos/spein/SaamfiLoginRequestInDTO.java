package co.edu.icesi.dev.saamfi.dtos.spein;

/**
 * Class for handling username and password values in the login request
 *
 * @author Luis Miguel Paz
 *
 */
public class SaamfiLoginRequestInDTO {

	/*
	 * String for the username
	 */
	private String username;

	/**
	 * String for the password
	 */
	private String password;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}