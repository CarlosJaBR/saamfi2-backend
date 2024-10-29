package co.edu.icesi.dev.saamfi.dtos.speout;

public class SaamfiLoginFullSpeOutDTO {

	private long userId;
	private String userUsername;
	private String userExtId;
	private String userEmail;
	private String userPhone;
	private String userName;
	private String userLastname;
	private String userDocumentId;

	private String accessToken;
	private String tokenType;

	private String systemHomePage;

	/**
	 * @param userDocumentId the userDocumentId to set
	 */
	public void setUserDocumentId(String userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	/**
	 * @return the userDocumentId
	 */
	public String getUserDocumentId() {
		return userDocumentId;
	}

	/**
	 * @return the systemHomePage
	 */
	public String getSystemHomePage() {
		return systemHomePage;
	}

	/**
	 * @param systemHomePage the systemHomePage to set
	 */
	public void setSystemHomePage(String systemHomePage) {
		this.systemHomePage = systemHomePage;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserExtId() {
		return userExtId;
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

	public String getUserUsername() {
		return userUsername;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserExtId(String userExtId) {
		this.userExtId = userExtId;
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

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

}