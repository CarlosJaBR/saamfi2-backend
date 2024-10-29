package co.edu.icesi.dev.saamfi.dtos.speout;

public class SaamfiUserSpeOutDTO {

	private long userId;

	private String userPhone;

	private String userEmail;

	private String userIsActive;

	private String userDocumentId;

	private String userLastname;

	private String userName;
	
	private String userUsername;
	
	

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserIsActive() {
		return userIsActive;
	}

	public void setUserIsActive(String userIsActive) {
		this.userIsActive = userIsActive;
	}

	public String getUserDocumentId() {
		return userDocumentId;
	}

	public void setUserDocumentId(String userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}