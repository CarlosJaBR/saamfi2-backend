package co.edu.icesi.dev.saamfi.dtos.spein;

/**
 * Class for handling JWT token
 *
 * @author Luis Miguel Paz V
 *
 */
public class JwtAuthenticationRespInDTO {

	/**
	 * Authorization token
	 */
	private String accessToken;

	/**
	 * Type of authorization token provided, at the moment just Bearer type token.
	 */
	private String tokenType = "Bearer";

	/**
	 * 
	 * @param accessToken generated
	 */
	public JwtAuthenticationRespInDTO(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}