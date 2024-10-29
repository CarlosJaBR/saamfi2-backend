package co.edu.icesi.dev.saamfi.dtos.speout;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * @author: Angie Valentina Córdoba - Alejandra Gonzalez Velez
 * Class in charge of get and store the google response for a reCaptcha query
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "success", "score", "challenge_ts", "hostname", "error-codes" })
public class ReCaptchaResponse {

	static enum ErrorCode {
		MissingSecret, InvalidSecret, MissingResponse, InvalidResponse;

		private static Map<String, ErrorCode> errorsMap = new HashMap<>(4);

		static {
			errorsMap.put("missing-input-secret", MissingSecret);
			errorsMap.put("invalid-input-secret", InvalidSecret);
			errorsMap.put("missing-input-response", MissingResponse);
			errorsMap.put("invalid-input-response", InvalidResponse);
		}

		@JsonCreator
		public static ErrorCode forValue(String value) {
			return errorsMap.get(value.toLowerCase());
		}
	}

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("score")
	private double score;

	@JsonProperty("challenge_ts")
	private Date challengeTs;

	@JsonProperty("hostname")
	private String hostname;

	@JsonProperty("error-codes")
	private ErrorCode[] errorCodes;

	public Date getChallengeTs() {
		return challengeTs;
	}

	public ErrorCode[] getErrorCodes() {
		return errorCodes;
	}

	public String getHostname() {
		return hostname;
	}

	public double getScore() {
		return score;
	}

	@JsonIgnore
	public boolean hasClientError() {
		ErrorCode[] errors = getErrorCodes();
		if (errors == null) {
			return false;
		}
		for (ErrorCode error : errors) {
			switch (error) {
				case InvalidResponse:
				case MissingResponse:
					return true;
				default:
					return false;

			}
		}
		return false;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setChallengeTs(Date challengeTs) {
		this.challengeTs = challengeTs;
	}

	public void setErrorCodes(ErrorCode[] errorCodes) {
		this.errorCodes = errorCodes;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
