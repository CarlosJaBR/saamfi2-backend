package co.edu.icesi.dev.saamfi.dtos.spein;

public class SaamfiUserProfileInDTO {
	
	private Long userId;
	
	private String userPhone;

	private String userEmail;

	private String userLastname;

	private String userName;
	
	private String userPassword;

	private String userPasswordConfirmation;

	public SaamfiUserProfileInDTO() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPasswordConfirmation() {
		return userPasswordConfirmation;
	}

	public void setUserPasswordConfirmation(String userPasswordConfirmation) {
		this.userPasswordConfirmation = userPasswordConfirmation;
	}


}
