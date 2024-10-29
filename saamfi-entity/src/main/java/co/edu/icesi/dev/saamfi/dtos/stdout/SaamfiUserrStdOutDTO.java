package co.edu.icesi.dev.saamfi.dtos.stdout;

public class SaamfiUserrStdOutDTO {

	private long userId;

	private String userUsername;

	private long userExtId;

	public long getUserExtId() {
		return userExtId;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserExtId(long userExtId) {
		this.userExtId = userExtId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

}