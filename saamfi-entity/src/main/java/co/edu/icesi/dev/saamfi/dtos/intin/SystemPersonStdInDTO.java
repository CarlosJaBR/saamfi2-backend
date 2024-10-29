package co.edu.icesi.dev.saamfi.dtos.intin;

public class SystemPersonStdInDTO {

	private long userId;

	private String userPhone;

	private String userEmail;

	private String userDocumentId;

	private String persIsactive;

	private String userLastname;

	private String userName;

	public String getPersIsactive() {
		return persIsactive;
	}

	public String getUserDocumentId() {
		return userDocumentId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setPersIsactive(String persIsactive) {
		this.persIsactive = persIsactive;
	}

	public void setUserDocumentId(String userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}