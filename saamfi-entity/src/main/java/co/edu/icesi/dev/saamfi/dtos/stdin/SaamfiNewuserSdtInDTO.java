package co.edu.icesi.dev.saamfi.dtos.stdin;

public class SaamfiNewuserSdtInDTO {

	private String userEmail;
	private String userDocumentId;
	private String userUsername;
	private String userName;
	private String userLastname;
	private String userPhone;
	private String userIsActive;
	private String userPassword;
	private long   userInstitution;
	
	
	
	
	
	
	public long getUserInstitution() {
		return userInstitution;
	}
	public void setUserInstitution(long userInstitution) {
		this.userInstitution = userInstitution;
	}
	
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserIsActive() {
		return userIsActive;
	}
	public void setUserIsActive(String userIsActive) {
		this.userIsActive = userIsActive;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserDocumentId() {
		return userDocumentId;
	}
	public void setUserDocumentId(String userDocumentId) {
		this.userDocumentId = userDocumentId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLastname() {
		return userLastname;
	}
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	}
