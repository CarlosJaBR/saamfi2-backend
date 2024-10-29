package co.edu.icesi.dev.saamfi.dtos.spein;

import java.io.Serializable;

public class SaamfiUserChangePassSpeInDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1995917580603478997L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String userUsername;

	private String userPassword;

	private String userPasswordConfirmation;
	
	private String code;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserPasswordConfirmation() {
		return userPasswordConfirmation;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserPasswordConfirmation(String userPasswordConfirmation) {
		this.userPasswordConfirmation = userPasswordConfirmation;
	}
	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}


}