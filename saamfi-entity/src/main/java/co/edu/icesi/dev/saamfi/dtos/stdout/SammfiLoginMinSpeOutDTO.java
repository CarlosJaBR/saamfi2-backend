package co.edu.icesi.dev.saamfi.dtos.stdout;

public class SammfiLoginMinSpeOutDTO {

	private long userId;

	private String userName;

	private String userLastname;

	public long getUserId() {
		return userId;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public String getUserName() {
		return userName;
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

}