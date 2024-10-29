package co.edu.icesi.dev.saamfi.dtos.spein;

public class SaamfiUserSpeInDTO {

	private String userUsername;

	private String userPassword;

	private String userPasswordConfirmation;

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserPasswordConfirmation() {
		return userPasswordConfirmation;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserPasswordConfirmation(String userPasswordConfirmation) {
		this.userPasswordConfirmation = userPasswordConfirmation;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

}