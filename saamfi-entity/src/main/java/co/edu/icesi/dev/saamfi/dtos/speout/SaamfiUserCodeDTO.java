package co.edu.icesi.dev.saamfi.dtos.speout;

public class SaamfiUserCodeDTO {
	
	public SaamfiUserCodeDTO() {
		
	}	
	private String userEmail;
	
	private String codeValidePassword;
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCodeValidePassword() {
		return codeValidePassword;
	}

	public void setCodeValidePassword(String codeValidePassword) {
		this.codeValidePassword = codeValidePassword;
	}


}
