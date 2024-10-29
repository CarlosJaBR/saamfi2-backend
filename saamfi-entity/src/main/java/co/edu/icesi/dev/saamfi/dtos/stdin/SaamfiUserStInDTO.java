package co.edu.icesi.dev.saamfi.dtos.stdin;

public class SaamfiUserStInDTO {

	private String userPassword;

	private String userUsername;

	private String userPasswordConfirmation;

	private String userPhone;

	private String userEmail;

	private String userDocumentId;

	private String userIsActive;

	private String userLastname;

	private String userName;

	public String getUserDocumentId() {
		return userDocumentId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserIsActive() {
		return userIsActive;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserPasswordConfirmation() {
		return userPasswordConfirmation;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserDocumentId(String userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserIsActive(String userIsactive) {
		this.userIsActive = userIsactive;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserPasswordConfirmation(String userPasswordConfirmation) {
		this.userPasswordConfirmation = userPasswordConfirmation;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

}
